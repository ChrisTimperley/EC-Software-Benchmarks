import random
import math

from deap import base
from deap import creator
from deap import tools
from deap import algorithms

creator.create("FitnessMin", base.Fitness, weights=(-1.0,))
creator.create("Individual", list, fitness=creator.FitnessMin)

toolbox = base.Toolbox()
toolbox.register("attr_float", random.uniform, -5.12, 5.12)
toolbox.register("individual", tools.initRepeat, creator.Individual, 
    toolbox.attr_float, 100)
toolbox.register("population", tools.initRepeat, list, toolbox.individual)

def evaluate(individual):
  f = 10.0 * len(individual)
  for x in individual:
    f += x*x - 10.0 * math.cos(2.0 * math.pi * x)
  return f,

# Operator registering
toolbox.register("evaluate", evaluate)
toolbox.register("mate", tools.cxOnePoint)
toolbox.register("mutate", tools.mutGaussian, mu=0.0, sigma=1.0, indpb=0.01)
toolbox.register("select", tools.selTournament, tournsize=2)

# RNG seed
random.seed(64)

# Algorithm
algorithms.eaSimple(toolbox.population(n=100), toolbox, cxpb=1.0, mutpb=1.0, ngen=1000,
  verbose=False)
