import pandas as pd
# import numpy as np
# import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn import tree

df = pd.read_csv(filepath_or_buffer = "capitalbikeshare.csv" )

df.head()

# breif info
df.info()


df.describe()

df["Member type"].unique()


# replace categorical data (Member type) with numbers

member_type = {
    "Member type": {
        "Member":0,
        "Casual":1,
        "Unknown":2
    }
}

df.replace(member_type, inplace=True)

df.head()


df[["Duration","Start station number","End station number","Member type"]].boxplot()


len(df["Duration"].unique())



X = df[["Duration","Start station number","End station number"]]
y = df["Member type"]
X_train, X_test, y_train,y_test = train_test_split(X,y,test_size = 0.30,random_state = 42)



model = tree.DecisionTreeClassifier()


model.fit(X_train,y_train)



y_pred = model.predict(X_test)

accuracy = accuracy_score(y_test, y_pred)

print("Accuracy of model : " , accuracy)

