# GAOneMax

"The OneMax Problem (or BitCounting) is a simple problem consisting in maximizing the number of ones of a bitstring."

Here we use a genetic algorithm for solve OneMax problem. This genetic algorithm was implemented as follows:

- The length of the binary string is ten.
- The selection operators are Uniform and Elitism.
- The mutation operator only the value of a bit within the individual is changed; the bit to change is selected randomly.
- The crossing operator is one-point crossing.
- The crossing and mutation operators probability is 0.7 and 0.3 respectively.  
- The replacement operators are Tournament and Roulette. 

The selection and replacement operators can use at the user's choice.

### Algorithm parameters

These parameters are required to run the algorithm:

- Population size
- Selection method
- Replacement method
- Number of iterations
