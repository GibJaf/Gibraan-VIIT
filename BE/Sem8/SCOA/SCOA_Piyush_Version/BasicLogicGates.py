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
print([and_nn.predict(x=x) for x in truth_tb_inp])

or_nn = McCullochPittsNN(weights=[-1, 1, 1], threshold=0)
print([or_nn.predict(x=x) for x in truth_tb_inp])

not_nn = McCullochPittsNN(weights=[0.5, -1], threshold=0)
print([not_nn.predict(x=x) for x in truth_tb_inp_not])

nor_nn = McCullochPittsNN(weights=[0.5, -1, -1], threshold=0)
print([nor_nn.predict(x=x) for x in truth_tb_inp])

nand_nn = McCullochPittsNN(weights=[1.5, -1, -1], threshold=0)
print([nand_nn.predict(x=x) for x in truth_tb_inp])
