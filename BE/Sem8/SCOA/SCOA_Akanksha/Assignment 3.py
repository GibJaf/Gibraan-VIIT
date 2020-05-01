# -*- coding: utf-8 -*-
"""
Created on Wed Apr 15 18:14:25 2020

@author: Aakanskha

"""

alpha = 0.1
x1 = 1
x2 = 0
y = 1
w11 = 1
w12 = 1
w21 = 1
w22 = 1
w31 = 1
w32 = 1
h1 = x1 * w11 + x2 * w12 
h2 = x1 * w21 + x2 * w22
y_hat = h1 * w31 + h2 * w32
y_hat
2
def loss(target, predicted):
    return ((target - predicted) ** 2) / 2
def print_update_weight(weight, val, gradient):
    print("Update {} value: {}. Gradient: {}".format(weight, val, gradient))
def output_layer(w31, w32):
    # Output Layer
    delta_L_by_y_hat = -(y - y_hat)
    delta_y_hat_by_w31 = h1

    delta_L_by_w31 = delta_L_by_y_hat * delta_y_hat_by_w31

    # update w31
    w31 = w31 - alpha * delta_L_by_w31
    print_update_weight('w31', w31, delta_L_by_w31)

    delta_L_by_y_hat = -(y - y_hat)
    delta_y_hat_by_w32 = h2

    delta_L_by_w32 = delta_L_by_y_hat * delta_y_hat_by_w32

    # update w32
    w32 = w32 - alpha * delta_L_by_w32
    print_update_weight('w32', w32, delta_L_by_w32)
    
output_layer(w31, w32)

def hidden_layer(w11, w12, w21, w22):
    # Hidden Layer neuron 1

    delta_L_by_h1 = -(y - y_hat) * w31
    delta_h1_by_w11 = x1

    delta_L_by_w11 = delta_L_by_h1 * delta_h1_by_w11

    # update w11
    w11 = w11 - alpha * delta_L_by_w11
    print_update_weight('w11', w11, delta_L_by_w11)

    delta_L_by_h1 = -(y - y_hat) * w31
    delta_h2_by_w12 = x2

    delta_L_by_w12 = delta_L_by_h1 * delta_h2_by_w12

    # update w12
    w12 = w12 - alpha * delta_L_by_w12
    print_update_weight('w12', w12, delta_L_by_w12)

    # Hidden Layer neuron 2

    delta_L_by_h2 = -(y - y_hat) * w32
    delta_h2_by_w21 = x1

    delta_L_by_w21 = delta_L_by_h2 * delta_h2_by_w21

    # update w21
    w21 = w21 - alpha * delta_L_by_w21
    print_update_weight('w21', w21, delta_L_by_w21)

    delta_L_by_h2 = -(y - y_hat) * w32
    delta_h2_by_w22 = x2

    delta_L_by_w22 = delta_L_by_h2 * delta_h2_by_w22

    # update w22
    w22 = w22 - alpha * delta_L_by_w22
    print_update_weight('w22', w22, delta_L_by_w22)
    
hidden_layer(w11, w12, w21, w22)

 