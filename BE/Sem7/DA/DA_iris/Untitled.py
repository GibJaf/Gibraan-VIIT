
# coding: utf-8

# In[73]:

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
get_ipython().magic('matplotlib inline')


# In[16]:

dataset = pd.read_csv('iris.csv')
dataset.head(10)
#dataset[0:10]


# In[18]:

dataset.shape


# In[20]:

dataset.columns


# In[22]:

dataset.dtypes


# In[25]:

print(len(dataset.columns.values))


# In[35]:

dataset['Sepal.Length'].describe()   #do same for each features


# In[34]:

dataset.describe()


# In[45]:

#for individual plotting
plt.hist(dataset['Sepal.Length'], bins = 10)
plt.ylabel('Number of times')
plt.xlabel('Sepal.Length')
plt.show()


# In[51]:

for i in range(1, 5):
    plt.hist(np.array(dataset)[:, i], bins = 10)
    plt.ylabel('Number of times')
    plt.xlabel(dataset.columns.values[i])
    plt.show()
    


# In[57]:

data = np.array(dataset)
for i in range(1, 5):
    plt.boxplot(np.array(data[:, i], dtype='float'))
    plt.show()


# In[106]:

for i in range(1, 5):
    sns.boxplot(x = np.array(dataset)[:, i])
    


# In[118]:

sns.boxplot(data=dataset.ix[:, 1:5])


# In[121]:

sns.boxplot(x=dataset['Species'], y=dataset['Sepal.Length'])

