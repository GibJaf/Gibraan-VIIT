# -*- coding: utf-8 -*-
"""
Created on Sun Sep 29 17:47:47 2019

@author: Nitin
"""

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

dataset = pd.read_csv("ccdata.csv")
X = dataset.iloc[:,[3,4]].values

from sklearn.cluster import DBSCAN 
dbscan = DBSCAN(eps = 3, min_samples=4)

model = dbscan.fit(X)
labels = model.labels_

from sklearn import metrics

sample_cores = np.zeros_like(labels, dtype=bool)
sample_cores[dbscan.core_sample_indices_] = True

n_clusters = len(set(labels))- (1 if -1 in labels else 0)
print(metrics.silhouette_score(X,labels))