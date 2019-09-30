from sklearn.datasets import fetch_20newsgroups 
news = fetch_20newsgroups(subset='all')

print("Number of articles: " + str(len(news.data)))
print("Number of diffrent categories: " + str(len(news.target_names)))

labels = news.target_names

#content of first document
print("\n".join(news.data[0].split("\n")[:]))

#train the model
from sklearn.model_selection import train_test_split
import time

def train(classifier, X, y):
    start = time.time()
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=11)

    classifier.fit(X_train, y_train)
    end = time.time()
    
    print("Accuracy: " + str(classifier.score(X_test, y_test)) + ", Time duration: " + str(end - start))
    return classifier

#feature extraction
#building a text classifier

from sklearn.naive_bayes import MultinomialNB
from sklearn.pipeline import Pipeline
from sklearn.feature_extraction.text import TfidfVectorizer

trial1 = Pipeline([ ('vectorizer', TfidfVectorizer()), ('classifier', MultinomialNB())])

train(trial1, news.data, news.target)

#first model is not always good 
#therefore model is made efficient by removing stop words

from nltk.corpus import stopwords

trial2 = Pipeline([ ('vectorizer', TfidfVectorizer(stop_words=stopwords.words('english'))),('classifier', MultinomialNB())])

train(trial2, news.data, news.target)

#alpha parameter of naive bayes classifier is still default
for alpha in [5, 0.5, 0.05, 0.005, 0.0005]:
    trial3 = Pipeline([('vectorizer', TfidfVectorizer(stop_words=stopwords.words('english'))),('classifier', MultinomialNB(alpha=alpha))])
    
train(trial3, news.data, news.target)

#Let’s ignore the words that appear fewer than 5 times in all documents and use min_dif parameter
trial4 = Pipeline([ ('vectorizer', TfidfVectorizer(stop_words=stopwords.words('english'), min_df=5)), ('classifier', MultinomialNB(alpha=0.005)) ])

train(trial4, news.data, news.target)

#stemming the data
import string
from nltk.stem import PorterStemmer
from nltk import word_tokenize

stopWords = set(stopwords.words('english'))
print(len(stopWords))
stem_stop_words=[]

for i in range(0,179):
      stemmer = PorterStemmer()
      stem_stop_words.append(stemmer.stem(stopwords.words('english')[i]))
      
stem_stop_words.append("'d")
stem_stop_words.append("'ll")
stem_stop_words.append('``')
stem_stop_words.append('becau')
stem_stop_words.append('could')
stem_stop_words.append('might')
stem_stop_words.append('must')
stem_stop_words.append("n't")
stem_stop_words.append('need')
stem_stop_words.append('r')
stem_stop_words.append('sha')
stem_stop_words.append('v')
stem_stop_words.append('wo')
stem_stop_words.append('would')

print(stem_stop_words)

def stemming_tokenizer(text):
    stemmer = PorterStemmer()
    return [stemmer.stem(w) for w in word_tokenize(text)]

trial5 = Pipeline([ ('vectorizer', TfidfVectorizer(tokenizer=stemming_tokenizer, stop_words=stem_stop_words + list(string.punctuation))), ('classifier', MultinomialNB(alpha=0.005))])

train(trial5, news.data, news.target)

#trying with different classifiers
#Support Vector Classification with stochastic gradient descent and linear SVC
from sklearn.linear_model import SGDClassifier
from sklearn.svm import LinearSVC

for classifier in [SGDClassifier(), LinearSVC()]:
    trial6 = Pipeline([('vectorizer', TfidfVectorizer(stop_words=stopwords.words('english') + list(string.punctuation))), ('classifier', classifier)])

train(trial6, news.data, news.target)

#Model Evaluation
from sklearn.metrics import confusion_matrix
import matplotlib.pyplot as plt
import seaborn as sns

start = time.time()
classifier = Pipeline([('vectorizer', TfidfVectorizer(stop_words=stopwords.words('english') + list(string.punctuation))),('classifier', LinearSVC(C=10))])
X_train, X_test, y_train, y_test = train_test_split(news.data, news.target, test_size=0.2, random_state=11)
classifier.fit(X_train, y_train)
end = time.time()

print("Accuracy: " + str(classifier.score(X_test, y_test)) + ", Time duration: " + str(end - start))

y_pred = classifier.predict(X_test)
conf_mat = confusion_matrix(y_test, y_pred)

# Plot confusion_matrix
fig, ax = plt.subplots(figsize=(15, 10))
sns.heatmap(conf_mat, annot = True, cmap = "Set3", fmt="d", xticklabels=labels, yticklabels=labels)
plt.ylabel('Actual')
plt.xlabel('Predicted')
plt.show()

#accuracy of each category with classification report
from sklearn import metrics
print(metrics.classification_report(y_test, y_pred, target_names=labels))
