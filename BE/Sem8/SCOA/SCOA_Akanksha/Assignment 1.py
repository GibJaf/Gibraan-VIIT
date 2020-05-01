# -*- coding: utf-8 -*-
"""
Created on Wed Apr 15 17:53:03 2020

@author: Aakanksha

"""

#import numpy as np

class McCullochPittsNN():
    def __init__(self, weights, threshold):
        self.weights = weights
        self.threshold = threshold

    def g(self, x):
        return sum([(xi * wi) for xi, wi in zip(self.weights, ([1] + x))])

    def f(self, value):
        if value >= self.threshold:
            return 1
        return 0

    def predict(self, x):
        return self.f(self.g(x))

truth_tb_inp = [[0, 0], [0, 1], [1, 0], [1, 1]]
truth_tb_inp_not = [[0], [1]]

and_nn = McCullochPittsNN(weights=[-1.5, 1, 1], threshold=0)
p1 = [and_nn.predict(x=x) for x in truth_tb_inp]
print("AND OPERATION : ",p1)

or_nn = McCullochPittsNN(weights=[-1, 1, 1], threshold=0)
p2 = [or_nn.predict(x=x) for x in truth_tb_inp]
print("OR OPERATION : ",p2)

not_nn = McCullochPittsNN(weights=[0.5, -1], threshold=0)
p3 = [not_nn.predict(x=x) for x in truth_tb_inp_not]
print("NOT OPERATION : ",p3)

nor_nn = McCullochPittsNN(weights=[0.5, -1, -1], threshold=0)
p4 = [nor_nn.predict(x=x) for x in truth_tb_inp]
print("NOR OPERATION : ",p4)

nand_nn = McCullochPittsNN(weights=[1.5, -1, -1], threshold=0)
p5 = [nand_nn.predict(x=x) for x in truth_tb_inp]
print("NAND OPERATION : ",p5)