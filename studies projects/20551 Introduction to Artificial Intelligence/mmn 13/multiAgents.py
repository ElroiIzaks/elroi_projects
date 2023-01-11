# multiAgents.py
# --------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


from util import manhattanDistance
from game import Directions
import random, util

from game import Agent

class ReflexAgent(Agent):
    """
    A reflex agent chooses an action at each choice point by examining
    its alternatives via a state evaluation function.

    The code below is provided as a guide.  You are welcome to change
    it in any way you see fit, so long as you don't touch our method
    headers.
    """


    def getAction(self, gameState):
        """
        You do not need to change this method, but you're welcome to.

        getAction chooses among the best options according to the evaluation function.

        Just like in the previous project, getAction takes a GameState and returns
        some Directions.X for some X in the set {NORTH, SOUTH, WEST, EAST, STOP}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices) # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        newFood = successorGameState.getFood()
        newGhostStates = successorGameState.getGhostStates()
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]
        "*** YOUR CODE HERE ***"

        #the area of the newPos (for staing away from the ghosts)
        newPosArea =  [successorGameState.generatePacmanSuccessor(action).getPacmanPosition() for action in successorGameState.getLegalActions()]

        # don't stop
        if action == 'Stop': return -1
        
        for specificGhostState in newGhostStates:
          # ghost in next position or in is area
          if specificGhostState.getPosition() == newPos or specificGhostState.getPosition() in newPosArea:
            # scared- white
            if specificGhostState.scaredTimer == 0: return -1
            else: return 1
        
        #food in next position
        if newFood.count() != currentGameState.getFood().count():
          return 1
        

        min_distance = min([manhattanDistance(newPos, x) for x in newFood.asList()])

        #if next move is not eating, randomly pick go there or not. more chancess for good moves       
        return random.randint(0,1)*(1/(min_distance+1))


def scoreEvaluationFunction(currentGameState):
    """
    This default evaluation function just returns the score of the state.
    The score is the same one displayed in the Pacman GUI.

    This evaluation function is meant for use with adversarial search agents
    (not reflex agents).
    """
    return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
    """
    This class provides some common elements to all of your
    multi-agent searchers.  Any methods defined here will be available
    to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

    You *do not* need to make any changes here, but you can if you want to
    add functionality to all your adversarial search agents.  Please do not
    remove anything, however.

    Note: this is an abstract class: one that should not be instantiated.  It's
    only partially specified, and designed to be extended.  Agent (game.py)
    is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
    """
    Your minimax agent (question 2)
    """

    def getAction(self, gameState):
        """
        Returns the minimax action from the current gameState using self.depth
        and self.evaluationFunction.

        Here are some method calls that might be useful when implementing minimax.

        gameState.getLegalActions(agentIndex):
        Returns a list of legal actions for an agent
        agentIndex=0 means Pacman, ghosts are >= 1

        gameState.generateSuccessor(agentIndex, action):
        Returns the successor game state after an agent takes an action

        gameState.getNumAgents():
        Returns the total number of agents in the game

        gameState.isWin():
        Returns whether or not the game state is a winning state

        gameState.isLose():
        Returns whether or not the game state is a losing state
        """
        "*** YOUR CODE HERE ***"
        
        startDepth = 0

        # find the minimax value
        minimaxValue, action = self.calcMinimaxValue(gameState, 0, startDepth)
        return action

    def calcMinimaxValue(self, gameState, agent, depth):

        # Leaves
        if self.endOfBranch(gameState, agent, depth):
          return self.evaluationFunction(gameState), None

        # making the next nodes
        newStates = self.generate_next_states_with_documentation(gameState, agent)
        
        # change agent position (go to next agent)
        nextAgent = (agent+1) % gameState.getNumAgents()
        
        minimaxes = self.get_minimax_val_for_each_move(newStates,nextAgent,depth)
        
        # return value acording to mission (min or max)
        # pacman
        if agent == 0: return max(minimaxes)
        # ghost
        else: return min(minimaxes)


    def endOfBranch(self, gameState, agent, depth):

        # end of branch length    
        if depth == self.depth: return True
        
        # no more legal moves
        if not gameState.getLegalActions(agent): return True
        
        # winning or losing (no reason to continue with the branch) 
        if gameState.isWin() or gameState.isLose(): return True
    
        return False

    def generate_next_states_with_documentation(self, gameState, agent):
        return [(gameState.generateSuccessor(agent, action), action) for action in gameState.getLegalActions(agent)]
  
    def get_minimax_val_for_each_move(self, gameStates, agent, depth):
        
        # if the next agent is pacman
        if agent == 0: depth += 1
        
        return [(self.calcMinimaxValue(state, agent, depth)[0], action) for state, action in gameStates]

class AlphaBetaAgent(MultiAgentSearchAgent):
    """
    Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
        Returns the minimax action using self.depth and self.evaluationFunction
        """
        "*** YOUR CODE HERE ***"
        alpha = float('-inf')
        beta = float('inf')
        startDepth = 0

        # find the minimax value with AlphaBeta pruning
        minimaxValue, action = self.calcMinimaxValue(gameState, 0, startDepth, alpha, beta)
        return action

    def calcMinimaxValue(self, gameState, agent, depth, alpha, beta):

        # Leaves
        if self.endOfBranch(gameState, agent, depth):
            return [self.evaluationFunction(gameState), None]

        legalActions = gameState.getLegalActions(agent)

        # change agent position (go to next agent)
        nextAgent = (agent+1) % gameState.getNumAgents()

        # if the next agent is pacman
        if nextAgent == 0: depth += 1

        if agent == 0: # if pacman turn
          bestMove = [float('-inf'), None]
          for action in legalActions: # from (N , A , S , W , STOP)
            state = gameState.generateSuccessor(agent, action)
            currentMove = [self.calcMinimaxValue(state, nextAgent, depth, alpha, beta)[0], action]
            bestMove = max(bestMove, currentMove)
            if bestMove[0] > beta: return bestMove
            alpha = max(alpha, bestMove[0])     
        else: # if ghosts turn   
          bestMove = [float('inf'), None]
          for action in legalActions:
            state = gameState.generateSuccessor(agent, action)
            currentMove = [self.calcMinimaxValue(state, nextAgent, depth, alpha, beta)[0], action]
            bestMove = min(bestMove, currentMove)
            if bestMove[0] < alpha: return bestMove
            beta = min(beta, bestMove[0])
            
        return bestMove

    def endOfBranch(self, gameState, agent, depth):

        # end of branch length    
        if depth == self.depth: return True
        
        # no more legal moves
        if not gameState.getLegalActions(agent): return True
        
        # winning or losing (no reason to continue with the branch) 
        if gameState.isWin() or gameState.isLose(): return True
    
        return False



class ExpectimaxAgent(MultiAgentSearchAgent):
    """
      Your expectimax agent (question 4)
    """

    def getAction(self, gameState):
        """
          Returns the expectimax action using self.depth and self.evaluationFunction

          All ghosts should be modeled as choosing uniformly at random from their
          legal moves.
        """
        "*** YOUR CODE HERE ***"
        startDepth = 0

        minimaxValue, action = self.calcMinimaxValue(gameState, 0, startDepth)
        return action

    def calcMinimaxValue(self, gameState, agent, depth):
        
        # Leaves
        if self.endOfBranch(gameState, agent, depth):
            return self.evaluationFunction(gameState), None

        newStates = [(gameState.generateSuccessor(agent, action), action) for action in gameState.getLegalActions(agent)]

        # change agent position (go to next agent)
        nextAgent = (agent+1) % gameState.getNumAgents()

        # if the next agent is pacman
        if nextAgent == 0: depth += 1

        minimaxes = [(self.calcMinimaxValue(state, nextAgent, depth)[0], action) for state, action in newStates]

        #if pacman
        if agent == 0: return max(minimaxes)
        #if ghost
        else:
          minimaxes_vals = [x[0] for x in minimaxes]
          avg = sum(minimaxes_vals) / float(len(minimaxes_vals))
          return (avg, None) #the avg has no direction...
    
    def endOfBranch(self, gameState, agent, depth):

        # end of branch length    
        if depth == self.depth: return True
        
        # no more legal moves
        if not gameState.getLegalActions(agent): return True
        
        # winning or losing (no reason to continue with the branch) 
        if gameState.isWin() or gameState.isLose(): return True
    
        return False

def betterEvaluationFunction(currentGameState):
    """
    Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
    evaluation function (question 5).

    DESCRIPTION: <write something here so we know what you did>
    """
    "*** YOUR CODE HERE ***"

    value = 0

    position = currentGameState.getPacmanPosition()
    foodList = currentGameState.getFood()
    ghostStates = currentGameState.getGhostStates()

    # winning or losing (no reason to continue with the branch) 
    if currentGameState.isWin(): return float('inf')
    if currentGameState.isLose(): return float('-inf')



    value -= len(foodList.asList())*100
    value -= random.random()/10*len(foodList.asList())

    for food in foodList.asList():
        value -= manhattanDistance(position,food)

    for ghost in ghostStates:
        value += manhattanDistance(position, ghost.getPosition())*2

    return value

# Abbreviation
better = betterEvaluationFunction
