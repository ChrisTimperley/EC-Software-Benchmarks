from pyevolve import G1DList, GSimpleGA, Selectors
from pyevolve import Initializators, Mutators, Consts, Crossovers
import math

# This is the Sphere Function
def rastrigin(xlist):
   n = len(xlist)
   total = 0
   for i in range(n):
      total += (xlist[i]**2)
   return total

# Genome instance
genome = G1DList.G1DList(100)
genome.setParams(rangemin=-5.12, rangemax=5.13, gauss_mu=0.0, gauss_sigma=1.0)
genome.initializator.set(Initializators.G1DListInitializatorReal)
genome.mutator.set(Mutators.G1DListMutatorRealGaussian)
genome.crossover.set(Crossovers.G1DListCrossoverSinglePoint)

# The evaluator function (objective function)
genome.evaluator.set(rastrigin)

# Genetic Algorithm Instance
ga = GSimpleGA.GSimpleGA(genome)
ga.minimax = Consts.minimaxType["minimize"]
ga.setGenerations(1000)
ga.setPopulationSize(100)
ga.setMutationRate(0.01)
ga.evolve()
