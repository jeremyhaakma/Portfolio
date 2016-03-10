#Matplotlib hill-climbing animation demo 
#Alistair Knott alik@cs.otago.ac.nz
#Based on an animation demo by Jake Vanderplas
#vanderplas@astro.washington.edu
#website: http://jakevdp.github.com

import numpy as np
from matplotlib import pyplot as plt
from matplotlib import animation
import math

plt.close('all')

# First set up the figure, the axis, and the plot element we want to animate (states)
fig = plt.figure()
ax = plt.axes(xlim=(0, 2), ylim=(-2, 2.5))
states, = ax.plot([], [], 'bo', ms=6)

def simple_fitness_fn(states):
    return np.sin(.5 * np.pi * states)
    
def complex_fitness_fn(states):
    component1 = 0.2 * np.sin(15 * np.pi * states)
    component2 = np.sin(2 * np.pi * (states+.25))
    component3 = states/1.5
    return component1+component2+component3

# Now define a fitness function. This takes a state (or an array of states), 
# and computes the associated fitness value (or array of values).
def fitness(states):
    return complex_fitness_fn(states) 
            
# Define a class to hold the current state of the search algorithm, with methods to 
# initialise the state and to update it.
class search_algorithm:
    
    def __init__(self, neighbour_distance, limits, number_of_steps):
        self.state = .5 #np.random.uniform(low=limits[0], high=limits[1])
        self.fitness = fitness(self.state)
        self.neighbour_distance = neighbour_distance
        self.neighbours = np.zeros(2)
        self.limits = limits
        self.number_of_steps = number_of_steps
        self.iteration = 0
        
        #define an annealing schedule for use in simulated annealing
        anneal_constant = -10
        anneal_values = np.linspace(500,0.01, number_of_steps)
        self.temp_schedule = np.exp(anneal_constant/anneal_values)
        
    def get_neighbours(self):
        self.neighbours[0] = self.state - self.neighbour_distance
        if self.neighbours[0] < self.limits[0]:
            self.neighbours[0] = self.limits[0]
        self.neighbours[1] = self.state + self.neighbour_distance
        if self.neighbours[1] > self.limits[1]:
            self.neighbours[1] = self.limits[1]
            
    def random_walk_update(self):
        self.get_neighbours()
        randomly_picked_neighbour = np.random.choice(self.neighbours)
        fitness_of_picked_neighbour = fitness(randomly_picked_neighbour)
        self.state = randomly_picked_neighbour
        self.fitness = fitness_of_picked_neighbour

    def show_temp_schedule(self):
        #Plots the way uphill probability decreases over the course of the search
        plt.figure()
        x = np.linspace(0,self.number_of_steps-1, self.number_of_steps)
        plt.plot(x,self.temp_schedule, 'g')
        plt.xlabel(r'$iteration$', fontsize=24)
        plt.ylabel(r'$p(current \leftarrow next)$', fontsize=24)
        plt.show()  
    def hill_climb_update(self):
        self.get_neighbours()
        if fitness(self.neighbours[0]) > fitness(self.state):
            self.state = self.neighbours[0]
            self.fitness = fitness(self.neighbours[0])
        if fitness(self.neighbours[1]) > fitness(self.state):
            self.state = self.neighbours[1]
            self.fitness = fitness(self.neighbours[1])
        
    def simulated_annealing_update(self):
        T = self.temp_schedule(self.iteration)
        self.get_neighbours()
        randomly_picked_neighbour = np.random.choice(self.neighbours)
        change_in_f = fitness(randomly_picked_neighbour) - self.fitness
        if change_in_f > 0:
            self.state = randomly_picked_neighbour
            self.fitness = fitness(randomly_picked_neighbour)
        elif np.random.rand < math.exp(change_in_f / T):
            self.state = randomly_picked_neighbour
            self.fitness = fitness(randomly_picked_neighbour)
        self.iteration += 1


        
                
# Define an instance of the algorithm         
alg = search_algorithm(neighbour_distance=.01, limits=np.array([0,2]), number_of_steps = 100)

#Now draw the curve to be followed
x = np.linspace(0, 2, 1000)
fitness_surface = fitness(x) 
plt.plot(x, fitness_surface, 'g')

# Initialization function: create the plot structure for each frame
def init():    
    states.set_data([],[])
    return states,

# Per-frame animation function. i is the frame number: the function is called once for each frame.
# This is where we define the updated state of the search
def animate(i):
    global alg

    #Update the current state and its associated fitness    
    alg.hill_climb_update()
    
    #Set the values of states for the current frame
    states.set_data(alg.state, alg.fitness)
    return states,

# Top-level animation function. 
# fig is the figure to be animated
# frames is the number of frames in the animation
# animate is the function to be called for each frame
# interval is the length each frame is displayed
# blit=True means only re-draw the parts that have changed.
anim = animation.FuncAnimation(fig, animate, init_func=init,
                               frames=alg.number_of_steps, interval=80, blit=True, repeat=False)

plt.show()