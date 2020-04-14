from sklearn import datasets, linear_model
from sklearn.model_selection import train_test_split
from genetic_selection import GeneticSelectionCV

dataset = datasets.load_iris()

x, y = dataset.data, dataset.target

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2)

clf = linear_model.LogisticRegression(solver="liblinear", multi_class="ovr")

selector = GeneticSelectionCV(clf, cv=5, verbose=1, scoring="accuracy", max_features=4,
                              n_population=50,
                              crossover_proba=0.5,
                              mutation_proba=0.2,
                              n_generations=40,
                              crossover_independent_proba=0.5,
                              mutation_independent_proba=0.05,
                              tournament_size=3,
                              n_gen_no_change=10,
                              caching=True,
                              n_jobs=1)

selector = selector.fit(x_train, y_train)

print(selector.score(x_test, y_test))