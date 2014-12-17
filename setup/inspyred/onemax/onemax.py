from random import Random
from time import time
import inspyred

def generate(random, args):
  return [random.choice([0, 1]) for i in range(args.get('length'))]

@inspyred.ec.evaluators.evaluator
def evaluate(i, args):
  return sum(i) / float(len(i))

def main():
  prng = Random()
  prng.seed(1) 
  
  ea = inspyred.ec.EvolutionaryComputation(prng)
  ea.terminator = inspyred.ec.terminators.generation_termination
  ea.selector = inspyred.ec.selectors.tournament_selection
  ea.replacer = inspyred.ec.replacers.generational_replacement
  ea.observer = inspyred.ec.observers.stats_observer
  ea.variator = [inspyred.ec.variators.n_point_crossover, inspyred.ec.variators.bit_flip_mutation]

  final_pop = ea.evolve(generator=generate,
                        evaluator=evaluate,
                        pop_size=1000,
                        max_generations=10000,
                        bounder=inspyred.ec.Bounder(0, 1),
                        length=100,
                        tournament_size=2,
                        mutation_rate=0.01,
                        crossover_rate=1.0,
                        num_crossover_points=1,
                        num_selected=1000,
                        maximize=True)
  
  return ea
          
if __name__ == '__main__':
  main()