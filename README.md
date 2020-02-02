# Rock-Paper-Scissors-Lizard-Spock App

RockPaperScissors App extended rules with lizard and spock + score keeping

_rules: https://www.instructables.com/id/How-to-Play-Rock-Paper-Scissors-Lizard-Spock/_

## How to run
- Use spring boot maven plugin (***You need to install and activate java 13 for this option (see https://www.jenv.be/)***):

```
./mvnw spring-boot:run
```

- Run via shell script (It basically pull and run docker image):
 
```
sh ./run.sh
```

- Use your IDE (enable preview language feature) and run 
 ```
com.github.cenkakin.rpslsapp.RPSLSAppApplication
```

## How to play

Possible Hands = ROCK, PAPER, SCISSORS, LIZARD, SPOCK

Ex request:
```curl -X POST -H "Content-Type: application/json" [localhost or your DOCKER_HOST]:4567/game -d '{"hand": "LIZARD"}'```

Ex response:
```{"result":"LOST","computerHand":"ROCK"}```

## Get current score

```curl [localhost or your DOCKER_HOST]:4567/score```

Ex Response:
```{"win":3,"lost":1,"draw":0}```

_Note: Score is not persisted after the application is shut down_ 



