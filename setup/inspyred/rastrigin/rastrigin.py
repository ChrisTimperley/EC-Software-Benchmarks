from random import Random
from time import time
import math
import inspyred

def generate(random, args):
  return [random.choice([0, 1]) for i in range(args.get('length'))]

@inspyred.ec.evaluators.evaluator
def evaluate(i, args):
  f = 10.0 * len(i)
  for x in i:
    f += x*x - 10.0 * math.cos(2.0 * math.pi * x)
  return f

def main():
  prng = Random()
  prng.seed(1) 
  
  ea = inspyred.ec.EvolutionaryComputation(prng)
  ea.terminator = inspyred.ec.terminators.generation_termination
  ea.selector = inspyred.ec.selectors.tournament_selection
  ea.replacer = inspyred.ec.replacers.generational_replacement
  ea.observer = inspyred.ec.observers.stats_observer
  ea.variator = [inspyred.ec.variators.n_point_crossover, inspyred.ec.variators.gaussian_mutation]

  final_pop = ea.evolve(generator=generate,
                        evaluator=evaluate,
                        pop_size=1000,
                        max_generations=10000,
                        bounder=inspyred.ec.Bounder(-5.12, 5.12),
                        length=100,
                        tournament_size=2,
                        mutation_rate=0.01,
                        gaussian_mean=0.0,
                        gaussian_stdev=1.0,
                        crossover_rate=1.0,
                        num_crossover_points=1,
                        num_selected=1000,
                        maximize=False)

  return ea
          
if __name__ == '__main__':
  main()