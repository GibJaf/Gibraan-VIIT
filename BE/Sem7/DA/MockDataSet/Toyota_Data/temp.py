# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

import os
os.chdir('../Toyota_Data')
print(os.getcwd())


# imports
import pandas as pd

# import data
cars_data = pd.read_csv('Toyota.csv',index_col=0,na_values=["??","????"])

cars_data.info()

cars_data.insert(10,"Price_Class","")

for i in range(0,len(cars_data['Price']),1):
    if cars_data['Price'][i] <= 8450:
        cars_data['Price_Class'][i] = "Low"
    elif cars_data['Price'][i] > 11950:
        cars_data['Price_Class'][i] = "High"
    else:
        cars_data['Price_Class'][i] = "Medium"

cars_data['Km_per_month'].value_counts()


# Convert Age (from months) to Age in years
cars_data.insert(11,"Age_Converted","")
def c_convert(val):
    val_converted = val/12
    return val_converted
cars_data['Age_Converted']= c_convert(cars_data['Age'])
cars_data['Age_Converted'] = round(cars_data["Age_Converted"],3)

cars_data.insert(12,"Km_per_month",0)

def c_convert(val1,val2):
    val_converted = val1/12
    ratio = val2/val1
    return [val_converted,ratio]

cars_data["Age_Converted"],cars_data["Km_per_month"] = c_convert(cars_data["Age"],cars_data['KM'])

print(cars_data['Age_Converted'])


# Exploratory data
cars_data2 = cars_data.copy()
pd.crosstab(index=cars_data2['FuelType'],columns='count',dropna=True)
pd.crosstab(index=cars_data2['Automatic'],columns=cars_data['FuelType'],dropna=True)