import random

class ScratchSVM:

    def __init__(self, 
                 learning_rate = 0.0001, 
                 lambda_param = 0.01, 
                 n_iters = 50):
        self.lr = learning_rate
        self.lambda_param = lambda_param    #Soft-margin control (1/C)
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

                # Compute dot product: w^T * x + b
                dot_compute = sum(wj * x_ij for wj, x_ij in zip(self.w, x_i))
                
                # Check condition: y_i * (w^T * x_i + b) >= 1
                condition = y_i * (dot_compute + self.b) >=1

                if condition:
                    self.w = [wj - self.lr * (2 * self.lambda_param * wj) for wj in self.w]
                else:
                    self.w = [wj - self.lr * (2 * self.lambda_param * wj - (y_i * xij)) 
                              for wj, xij in zip(self.w, x_i)]
                    self.b -= (self.lr * (-y_i))





