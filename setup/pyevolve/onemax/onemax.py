from pyevolve import G1DBinaryString
from pyevolve import GSimpleGA
from pyevolve import Mutators
from pyevolve import Crossovers
from pyevolve import Selectors

def evaluate(chromosome):
  return sum(chromosome) / float(len(chromosome))

genome = G1DBinaryString.G1DBinaryString(100)
genome.evaluator.set(evaluate)
genome.mutator.set(Mutators.G1DBinaryStringMutatorFlip)
genome.crossover.set(Crossovers.G1DBinaryStringXSinglePoint)

ga = GSimpleGA.GSimpleGA(genome)
ga.selector.set(Selectors.GTournamentSelector)
ga.setGenerations(1000)
ga.setPopulationSize(100)
ga.evolve()
