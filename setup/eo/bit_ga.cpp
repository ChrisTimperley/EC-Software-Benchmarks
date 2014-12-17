#include <stdexcept>
#include <iostream>
#include <strstream>
#include <eo>
#include <ga.h>

typedef eoBit<double> Indi;

double binary_value(const Indi & _indi) 
{ 
  double sum = 0; 
  for (unsigned i = 0; i < _indi.size(); i++) 
    sum += _indi[i]; 
  return sum; 
}

void main_function(int argc, char **argv) 
{
  const unsigned int SEED = 42;          // seed for random number generator 
  const unsigned int T_SIZE = 3;        // size for tournament selection 
  const unsigned int VEC_SIZE = 8;    // Number of bits in genotypes 
  const unsigned int POP_SIZE = 20;  // Size of population 
  const unsigned int MAX_GEN = 100;  // Maximum number of generation before STOP 
  const float CROSS_RATE = 0.8;          // Crossover rate 
  const double P_MUT_PER_BIT = 0.01; // probability of bit-flip mutation 
  const float MUT_RATE = 1.0;              // mutation rate

  rng.reseed(SEED);

  eoEvalFuncPtr<Indi> eval(binary_value);
  eoPop<Indi> pop; 
  for (unsigned int igeno=0; igeno<POP_SIZE; igeno++) { 
    Indi v;
    for (unsigned ivar=0; ivar<VEC_SIZE; ivar++) { 
      bool r = rng.flip(); // new value, random in {0,1} 
      v.push_back(r);          // append that random value to v 
    } 
    eval(v);
    pop.push_back(v);
  }

  // Print (sorted) intial population (raw printout)
  pop.sort();
  cout << "Initial Population" << endl; 
  cout << pop;

  ///////////////////////////////////// 
  // selection and replacement 
  ////////////////////////////////////
  // The robust tournament selection 
  eoDetTournamentSelect<Indi> select(T_SIZE);  // T_SIZE in [2,POP_SIZE]
  // The simple GA evolution engine uses generational replacement 
  // so no replacement procedure is needed
  ////////////////////////////////////// 
  // The variation operators 
  //////////////////////////////////////
  // 1-point crossover for bitstring 
  eo1PtBitXover<Indi> xover;

  // standard bit-flip mutation for bitstring 
  eoBitMutation<Indi>  mutation(P_MUT_PER_BIT);

  eoGenContinue<Indi> continuator(MAX_GEN); 

  ///////////////////////////////////////// 
  // the algorithm 
  //////////////////////////////////////// 
  // standard Generational GA requires as parameters 
  // selection, evaluation, crossover and mutation, stopping criterion
  eoSGA<Indi> gga(select, xover, CROSS_RATE, mutation, MUT_RATE,  
                                 eval, continuator); 
  // Apply algo to pop - that's it! 
  gga(pop); 


  // Print (sorted) initial population 
  pop.sort(); 
  cout << "FINAL Population\n" << pop << endl;
} 
// A main that catches the exceptions 
int main(int argc, char **argv) 
{ 
#ifdef _MSC_VER 
 //  rng.reseed(42); 
     int flag = _CrtSetDbgFlag(_CRTDBG_LEAK_CHECK_DF); 
       flag |= _CRTDBG_LEAK_CHECK_DF; 
     _CrtSetDbgFlag(flag); 
//    _CrtSetBreakAlloc(100); 
#endif 
     try 
     { 
             main_function(argc, argv); 
     } 
     catch(exception& e) 
     { 
             cout << "Exception: " << e.what() << '\n'; 
     } 
     return 1; 
}