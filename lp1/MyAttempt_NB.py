#!/usr/bin/env python
# coding: utf-8

# In[2]:


import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

from sklearn.model_selection import train_test_split

from sklearn.naive_bayes import GaussianNB
from sklearn.preprocessing import StandardScaler

from sklearn.metrics import accuracy_score
from sklearn.metrics import confusion_matrix
from sklearn.metrics import classification_report


# In[3]:


df = pd.read_csv('./PimaIndiansDiabetes.csv')


# In[4]:


df.head(20)


# # Graphs

# In[5]:


df.hist(bins=10,figsize=(15,10))
plt.show()


# In[7]:


df.isna().sum()


# In[5]:


# Replacing 0's with NaNs
df['Glucose'] = df['Glucose'].replace(0, )
df['BloodPressure'] = df['BloodPressure'].replace(0, np.nan) 
df['SkinThickness'] = df['SkinThickness'].replace(0, np.nan) 
df['Insulin'] = df['Insulin'].replace(0, np.nan)        
df['BMI'] = df['BMI'].replace(0, np.nan) 
df['DiabetesFunct'] = df['DiabetesFunct'].replace(0, np.nan) # useless cuz DiabetesFuct=0 for no instance
df['Age'] = df['Age'].replace(0, np.nan)# useless cuz Age=0 for no instance


# In[6]:


# Replacing NaNs with means
df['Glucose'].fillna(df['Glucose'].mean(), inplace=True)
df['BloodPressure'].fillna(df['BloodPressure'].mean(), inplace=True)
df['SkinThickness'].fillna(df['SkinThickness'].mean(), inplace=True)
df['Insulin'].fillna(df['Insulin'].mean(), inplace=True)
df['BMI'].fillna(df['BMI'].mean(), inplace=True)
df['DiabetesFunct'].fillna(df['DiabetesFunct'].mean(), inplace=True)
df['Age'].fillna(df['Age'].mean(), inplace=True)


# In[10]:


df.head()


# In[11]:


sns.boxplot(x='Class',y='Age' , data=df)


# In[12]:


sns.boxplot(x='Class',y='BMI' , data=df)


# In[ ]:


# Make more such boxplots for all features


# In[14]:


# Heatmap to check correlation
corr = df[df.columns].corr() #corr = correlation
sns.heatmap(corr, annot = True) #sns is used for data visualization using matplotlib


# In[7]:


# Select features
X = pd.DataFrame(data = df, columns = ["TimesPregnant","Glucose","BloodPressure","SkinThickness","BMI","Age","Insulin","DiabetesFunct"])
y = pd.DataFrame(data = df, columns = ["Class"])


# In[17]:


# Split dataset
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y,test_size=0.2, random_state=0)
# Here try varous values for test_size such as 0.1 , 0.2 , 0.3 , 0.4


# In[24]:


# Data Scaling
scaler = StandardScaler()
X = scaler.fit_transform(X)


# In[18]:


df.head()


# In[26]:


X


# In[27]:


from sklearn.naive_bayes import GaussianNB
NBmodel = GaussianNB()
NBmodel.fit(X_train , y_train)
acc_NB = NBmodel.score(X_test,y_test) *100
print(acc_NB)


# In[29]:


actual_naive = y_test
predict_naive = NBmodel.predict(X_test)
result_naive = confusion_matrix(actual_naive , predict_naive)
print('confusion matrix => \n',result_naive)
print('\nAccuracy score => ',accuracy_score(actual_naive , predict_naive))
#print('Classification report => \n',classification_report(actual_naive,predict_naive))


# In[50]:


df['Class'].value_counts()


# In[12]:


# Pie chart method 1
labels = 'Absent' , 'Present'
sizes = [df['Class'].value_counts()[0] , df['Class'].value_counts()[1]]
fig1 , ax1 = plt.subplots()
ax1.pie(sizes , labels=labels , autopct='%1.1f%%')


# In[13]:


# Pie chart method 2
df['Class'].value_counts().plot(kind='pie' , autopct='%1.2f%%')


# In[ ]:




