# import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn.metrics import confusion_matrix
from sklearn.naive_bayes import GaussianNB

df=pd.read_csv('/home/gibraan/Git_Projects/Gibraan-VIIT/BE/DA/Assignments/Assignment 2/PimaIndiansDiabetes.csv')
df.info()
df.head()
df.describe()
print(df.describe().to_string())

plt.figure(figsize=(8,4))
df.boxplot()
plt.show()

x=df[["TimesPregnant","GlucoseConcentration","BloodPrs","SkinThickness","Serum","BMI","DiabetesFunct","Age"]]
y=df["Class"]


X_train,X_test,Y_train,Y_test=train_test_split(x,y,test_size=0.2,random_state=42)
model=GaussianNB()
model.fit(X_train,Y_train)
y_pred=model.predict(X_test)
accuracy=accuracy_score(Y_test,y_pred)
print(accuracy)
cm=confusion_matrix(Y_test,y_pred)
print(cm)

print(df['Serum'].mean())

df['Serum']=df['Serum'].replace(0,df['Serum'].mean())

x=df[["TimesPregnant","GlucoseConcentration","SkinThickness","Serum","BMI","DiabetesFunct","Age"]]
y=df["Class"]


X_train,X_test,Y_train,Y_test=train_test_split(x,y,test_size=0.2,random_state=42)
model=GaussianNB()
model.fit(X_train,Y_train)
y_pred=model.predict(X_test)
accuracy=accuracy_score(Y_test,y_pred)
print(accuracy)
cm=confusion_matrix(Y_test,y_pred)
print(cm)
