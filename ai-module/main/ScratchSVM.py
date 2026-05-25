import random

class ScratchSVM:

    def __init__(self, 
                 learning_rate = 0.0001, 
                 lambda_param = 0.01, 
                 n_iters = 50):
        self.lr = learning_rate
        self.lambda_param = lambda_param
        self.n_iters = n_iters
        self.w = None
        self.b = None

    def fit(self, X, y):
        n_samples, n_features = len(X), len(X[0])
        # Initialize weights and bias to zeros
        self.w = [0.0] * n_features
        self.b = 0.0

        # Stochastic Gradient Descent (SGD)
        for epoch in range(self.n_iters):

            #Shuffle data manually
            indices = list(range(n_samples))
            random.shuffle(indices) 

            loss = 0

            for idx in indices:

                x_i = X[idx]
                y_i = y[idx]

                # Compute dot product: w^T * x
                dot_compute = sum(self.w[j] * x_i[j] for j in range(n_features) + self.b)
                

                # Check condition: y_i * (w^T * x_i + b) >= 1



