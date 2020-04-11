#!/usr/bin/env python
# coding: utf-8

# In[2]:


import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
import seaborn as sns


# In[3]:


df = pd.read_csv("./iris.csv")


# In[4]:


df.info()


# In[5]:


df.describe()


# In[6]:


df.head()


# In[54]:


x = df["sepal_length"] 
plt.figure(figsize = (15, 7)) 
plt.hist(x, bins = 20, color = "blue", edgecolor="black") 
plt.title("Sepal Length Histogram") 
plt.xlabel("Sepal_Length_cm") 
plt.ylabel("Frequency")
#plt.show() 
#plt.hist(df["sepal_length"],bins=20,edgecolor="black")


# In[10]:


x = df["sepal_width"] 
plt.figure(figsize = (15, 7)) 
plt.hist(x, bins = 20, color = "blue", edgecolor="black") 
plt.title("Sepal Width Histogram") 
plt.xlabel("Sepal_Width_cm") 
plt.ylabel("Frequency")
plt.show() 


# In[11]:


x = df["petal_length"] 
plt.figure(figsize = (15, 7)) 
plt.hist(x, bins = 20, color = "blue", edgecolor="black") 
plt.title("Petal Length Histogram") 
plt.xlabel("Petal_Length_cm") 
plt.ylabel("Frequency")
plt.show() 


# In[12]:


x = df["petal_width"] 
plt.figure(figsize = (15, 7)) 
plt.hist(x, bins = 20, color = "blue", edgecolor="black") 
plt.title("Petal Width Histogram") 
plt.xlabel("Petal_Length_cm") 
plt.ylabel("Frequency")
plt.show() 


# In[34]:





# In[55]:


sns.boxplot(df['sepal_length'])


# In[43]:


sns.boxplot(x="species" , y="sepal_length" , data=df)


# In[47]:


sns.boxplot(x="species" , y="sepal_width" , data=df)


# In[48]:


sns.boxplot(x="species" , y="petal_length" , data=df)


# In[49]:


sns.boxplot(x="species" , y="petal_width" , data=df)


# In[44]:


setosa = df[df['species']=='setosa']['sepal_length']
versicolor = df[df['species']=='versicolor']['sepal_length']
virginica = df[df['species']=='virginica']['sepal_length']

fig = plt.figure()
ax = fig.add_subplot(111)
ax.boxplot([setosa,versicolor,virginica])


# In[46]:


df.boxplot(column='sepal_length',by='species')
plt.title("")


# In[4]:


# All boxplots together
df.boxplot()


# In[6]:


# Another easy way to create histograms
# df.hist()
df.hist(bins=10,figsize=(15,10)) # Decorate it if you want 


# In[ ]:




