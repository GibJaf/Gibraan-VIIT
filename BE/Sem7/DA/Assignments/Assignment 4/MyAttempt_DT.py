#!/usr/bin/env python
# coding: utf-8

# In[1]:


# Types of Decision trees
# 1) ID3 ( Iterative Dichotomiser 3)
# 2) C4.5 (sucessor of ID3)
# 3) C5.0 
# 4) M5
# 5) Decision Stump
# 6) MARS
# 7) CART (Classification And Regression Tree)
# 8) Conditional Inference Trees


# In[2]:


import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

from sklearn.model_selection import train_test_split

from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier

from sklearn.metrics import accuracy_score
from sklearn.metrics import confusion_matrix


# In[3]:


df = pd.read_csv('capitalbikeshare.csv')


# # Data Analysis

# In[17]:


df.info()


# In[4]:


df.head()


# In[7]:


df.describe()


# In[18]:


df['Member type'].value_counts()


# # Graphs

# In[8]:


x = df[['Duration' , 'Start station number' , 'End station number']]
y = df['Member type']


# In[27]:


y.value_counts().plot(kind='pie' , autopct='%1.2f%%')
plt.show()


# In[26]:


plt.figure(figsize=(20,10))
x.boxplot()
plt.show()


# In[33]:


df.hist(bins=20,figsize=(15,10))
plt.show()


# In[35]:


corr = df[df.columns].corr()
sns.heatmap(corr,annot=True)


# # Split data

# In[30]:


X_train , X_test , Y_train , Y_test = train_test_split(x,y,test_size=0.2 , random_state=0)


# # Decision Tree

# In[31]:


model = DecisionTreeClassifier()
model.fit(X_train,Y_train)
Y_pred = model.predict(X_test)


# In[39]:


accuracy2 = accuracy_score(Y_test , model.predict(X_test))* 100
accuracy2


# In[42]:


cm = confusion_matrix(Y_test , model.predict(X_test))
print(cm)


# In[43]:


sns.heatmap(cm,annot=True)

