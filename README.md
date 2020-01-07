Configuration:
	1) Import project as a maven project
	2) Update the maven project
	
Run the project:
Right click on PlayGame.java>run as java application
Enter the input on console as - 
5 E
2
Q 1 1 A1 B2
P 2 1 D4 C3
A1 B2 B2 B3
A1 B2 B3 A1 D1 E1 D4 D4 D5 D5

Expected result - 
Player-1 fires a missile with target A1 which got miss
Player-2 fires a missile with target A1 which got hit
Player-2 fires a missile with target B2 which got miss
Player-1 fires a missile with target B2 which got hit
Player-1 fires a missile with target B2 which got hit
Player-1 fires a missile with target B3 which got miss
Player-2 fires a missile with target B3 which got miss
Player-1 has no more missiles left to launch
Player-2 fires a missile with target A1 which got hit
Player-2 fires a missile with target D1 which got miss
Player-1 has no more missiles left to launch
Player-2 fires a missile with target E1 which got miss
Player-1 has no more missiles left to launch
Player-2 fires a missile with target D4 which got hit
Player-2 fires a missile with target D4 which got miss
Player-1 has no more missiles left to launch
Player-2 fires a missile with target D5 which got hit
Player-2 won the battle

