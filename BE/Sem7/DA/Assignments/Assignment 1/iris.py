import pandas as pd
# import numpy as np
import matplotlib.pyplot as plt

dataframe=pd.read_csv(filepath_or_buffer='/home/gibraan/Git_Projects/Gibraan-VIIT/BE/DA/Assignments/Assignment 1/iris.csv')
dataframe.describe()
dataframe.head(10)
dataframe.info()

dataframe.hist()

x = dataframe["sepal_length"] 

plt.figure(figsize = (15, 7)) 
plt.hist(x, bins = 20, color = "blue", edgecolor="black") 
plt.title("Sepal Length Histogram") 
plt.xlabel("Sepal_Length_cm") 
plt.ylabel("Frequency")
plt.show() 

 
dd=dataframe[dataframe['species'] == "setosa"]
dd.boxplot()

x = dataframe["sepal_width"]
plt.figure(figsize = (10, 5)) 
plt.hist(x, bins = 15, color = "blue", edgecolor="black") 
plt.title("Sepal Width Histogram") 
plt.xlabel("Sepal_Width_Cm") 
plt.ylabel("Frequency")
plt.show() 

dataframe.boxplot()

