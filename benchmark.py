#!/usr/bin/python
import sys
import os
import csv
import timeit
import subprocess
from tempfile import NamedTemporaryFile
from decimal import *

def ensure_dir(d):
  if not os.path.exists(d):
    os.makedirs(d)

# The number of runs to perform
RUNS = 100

# Extract the name of the package and the experiment.
package = sys.argv[1]
experiment = sys.argv[2]

# Ensure the directory for this package exists within the results directory.
ensure_dir("results")
ensure_dir("results/{0}".format(package))

# Create the CSV result file for this experiment.
with open('results/{0}/{1}.csv'.format(package, experiment), 'wb') as f:
  w = csv.writer(f)

  # Write the CSV file header.
  w.writerow(["real", "user", "sys"])
  
  print "Running {0}/{1}".format(package, experiment)

  # Change to the experiment directory.
  os.chdir("setup/{0}/{1}/".format(package, experiment))

  # Prepare the experiment.
  subprocess.call(["sh", "setup.sh"], shell=True)

  # Create the timing script.
  with open('timer.sh', 'w') as tf:
    tf.write("(time (sh run.sh &> nul)) &> times.txt")

  # Repeat the experiment a given number of times.
  for i in range(RUNS):
    subprocess.call(["sh", "timer.sh"], shell=True)

    # Read, format and insert the times.
    with open('times.txt', 'r') as times:
      times = [t.split("\t")[1][:-2] for t in times.readlines()[1:]]
      times = map(lambda t: t.split("m"), times)
      times = map(lambda t: str(Decimal(t[0]) * Decimal(60) + Decimal(t[1])), times)
      w.writerow(times)

    # Inform shell.
    print "Run {0}/{1}: {2}/{3} ({4}s)".format(i, RUNS, package, experiment, times[0])

  # Destroy the timing script.
  os.remove('timer.sh')

  # Return to the main EvoTools directory.
  os.chdir("../../../")