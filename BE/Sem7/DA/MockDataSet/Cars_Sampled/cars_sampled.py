# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

import os
print(os.getcwd())
#os.chdir("/home/gibraan/Git_Projects/Gibraan-VIIT/BE/DA/MockDataSet/Cars_Sampled")

import pandas as pd
import numpy as np
import matplotlib as mlt
import seaborn as sns

cars_sampled = pd.read_csv("cars_sampled.csv")

cars_sampled.isna().sum()

cars_sampled['price'].describe()

cars_sampled['vehicleType'].value_counts()
