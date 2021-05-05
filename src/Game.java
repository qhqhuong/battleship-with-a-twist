/**
 * GAME class.
 * 
 * @author Quynh Huong Nguyen 
 * @version 3 (21 Oct 2018)
 */
public class Game 
{
    private ShipList playerShips;
    private ShipList computerShips;

    public Game() 
    {
    }

    public Game(ShipList playerShips, ShipList computerShips) 
    {
        this.playerShips = playerShips;
        this.computerShips = computerShips;
    }

    public ShipList getPlayerShips() 
    {
        return this.playerShips;
    }

    public void setPlayerShips(ShipList playerShips) 
    {
        this.playerShips = playerShips;
    }

    public ShipList getComputerShips() 
    {
        return this.computerShips;
    }

    public void setComputerShips(ShipList computerShips) 
    {
        this.computerShips = computerShips;
    }

    public void displayMenu(int gridSize, int numberOfShips, boolean multiHitAllowed, boolean computerShipVisibled)
    {
        System.out.println("+=========================================================================+");
        System.out.println("|                                                                         |");
        System.out.println("|              Welcome to the Battleship Game -- With a Twist!!           |");
        System.out.println("|                                                                         |");
        System.out.println("+=========================================================================+");
        System.out.println("The game will use the grip size difined in the settings file.");
        System.out.printf("Playing grid size set as %d X %d %n", gridSize, gridSize);
        System.out.printf("Maximum number of ships allowed as %d %n", numberOfShips);
        System.out.printf("Multiple hit allowed per ship set as %b %n", multiHitAllowed);
        System.out.printf("Computer Ships Visibled %b %n", computerShipVisibled);
        System.out.print("Press ENTER key to continue...");
        Input inputReader = new Input();
        inputReader.readKeyboardInput();
        System.out.println("+=========================================================================+");
    }
    
    public void startGame() 
    {
        int playerScore = 0;
        int computerScore = 0;
        int round = 1;

        Game newGame = new Game();

        String[] settings = newGame.loadSettingFile();
        int gridHeight = Integer.parseInt(settings[0]);
        int gridWidth = Integer.parseInt(settings[0]);
        boolean multiHitAllowed = Boolean.parseBoolean(settings[1]);
        boolean computerShipVisibled = Boolean.parseBoolean(settings[2]);
        int nShips = Integer.parseInt(settings[3]);
        // 4
        newGame.displayMenu(gridHeight, nShips, multiHitAllowed, computerShipVisibled);

        newGame.generateComputerFleet(gridWidth, gridWidth, multiHitAllowed, nShips);
        newGame.generatePlayerFleet(gridWidth, gridWidth, multiHitAllowed, nShips);

        Input inputReader = new Input();

        boolean playerFleetIsWipedOut = false;
        boolean computerFleetIsWipedOut = false;
        while (true) 
        {
            System.out.printf("Beginning Round %d%n", round);
            System.out.printf("Player score %d%n", playerScore);
            System.out.printf("Computer score %d%n", computerScore);

            System.out.println("Displaying player board");
            newGame.printGameBoard(gridWidth, gridWidth, true, newGame.playerShips);

            System.out.println("----------");

            System.out.println("Displaying computer board");
            newGame.printGameBoard(gridWidth, gridWidth, computerShipVisibled, newGame.computerShips);

            boolean playerHit = newGame.makePlayerGuess(gridWidth, gridHeight);
            if (playerHit) 
            {
                playerScore += 10;//playerScore = playserScore + 10
                computerFleetIsWipedOut = newGame.computerShips.isWipedOut();
                if (computerFleetIsWipedOut) 
                {
                    break;
                }
            }

            boolean computerHit = newGame.makeComputerGuess(gridWidth, gridHeight);
            if (computerHit) 
            {
                computerScore += 10;
                playerFleetIsWipedOut = newGame.playerShips.isWipedOut();
                if (playerFleetIsWipedOut) 
                {
                    break;
                }
            }
            System.out.printf("Finish round %d%n", round);
            System.out.print("Press ENTER key to continue...");
            inputReader.readKeyboardInput();
            System.out.println("\u000C");
            round++; // round = round + 1 // round += 1

        }

        FileIO writter = new FileIO("result.txt");
        String result = "";

        if (playerFleetIsWipedOut) 
        {
            result = "COMPUTER wins. Computer score: " + Integer.toString(computerScore) + ". Player score: " + Integer.toString(playerScore);
            System.out.printf("Computer score %d%n", computerScore);
            System.out.println("COMPUTER wins");
        }

        if (computerFleetIsWipedOut) 
        {
            result = "PLAYER wins. Player score: " + Integer.toString(playerScore) +  ". Computer score: " + Integer.toString(computerScore);
            System.out.printf("Player score %d%n", playerScore);
            System.out.println("PLAYER wins");
        }
        writter.writeFile(result);

    }

    public void generateGameBoard(int gridWidth, int gridHeight) 
    {
    }

    public void generateComputerFleet(int gridWidth, int gridHeight, boolean multiHit, int nShips) {
        computerShips = new ShipList();
        CoordinateGenerator generator = new CoordinateGenerator();
        for (int n = 1; n <= nShips; n++) 
        {
            String shipName = "Comp Ship " + Integer.toString(n);
            int x = -1; // x = 100, x=1000
            int y = -1;
            do 
            {
                x = generator.generateRandomNumber(0, gridWidth - 1);
                y = generator.generateRandomNumber(0, gridHeight - 1);
            } while (computerShips.hasShipAtCoordinate(x, y));
            int noOfHitsNeeded = 1;
            if (multiHit) 
            {
                noOfHitsNeeded = generator.generateRandomNumber(1, 5);//[1,5]
            }

            computerShips.addShip(shipName, x, y, noOfHitsNeeded);
        }

    }

    public void generatePlayerFleet(int gridWidth, int gridHeight, boolean multiHit, int nShips) 
    {
        playerShips = new ShipList();
        CoordinateGenerator generator = new CoordinateGenerator();
        Validation validation = new Validation();
        Input inputReader = new Input();
        while (playerShips.getNumberOfShips() < nShips) 
        {
            boolean isInvalidInput = true;
            System.out.println("Loading player settings:");
            System.out.printf("Please enter the details for %d ship:%n", playerShips.getNumberOfShips() + 1);
            String shipName = "";
            do 
            {
                System.out.println("ShipName:");
                shipName = inputReader.readKeyboardInput();
                isInvalidInput = !validation.validateShipName(shipName);
                if (isInvalidInput) {
                    System.out.println("Ship name is invalid !!!");
                }
            } while (isInvalidInput);
            int xPos = -1;
            int yPos = -1;
            String inputString = "";
            do 
            {
                System.out.printf("Ship x Position (%d - %d):%n", 0, gridWidth - 1);
                inputString = inputReader.readKeyboardInput();
                isInvalidInput = !validation.validateCoordinate(inputString, gridWidth);
                if (isInvalidInput) 
                {
                    System.out.println("Invalid ship x position");
                    continue;
                } 
                else 
                {
                    xPos = Integer.parseInt(inputString);
                }

                System.out.printf("Ship y Position (%d - %d):%n", 0, gridHeight - 1);
                inputString = inputReader.readKeyboardInput();
                isInvalidInput = !validation.validateCoordinate(inputString, gridHeight);
                if (isInvalidInput) 
                {
                    System.out.println("Invalid ship y position");
                    continue;
                } 
                else 
                {
                    yPos = Integer.parseInt(inputString);
                }

                isInvalidInput = playerShips.hasShipAtCoordinate(xPos, yPos);
                if (isInvalidInput) 
                {
                    System.out.printf("There is already one ship at (%d, %d)%n", xPos, yPos);
                }
            } while (isInvalidInput);
            int noOfHitsNeeded = 1;
            if (multiHit) 
            {
                noOfHitsNeeded = generator.generateRandomNumber(1, 5);
            }
            playerShips.addShip(shipName, xPos, yPos, noOfHitsNeeded);
        }
    }

    public boolean makeComputerGuess(int gridWidth, int gridHeight) 
    {
        CoordinateGenerator generator = new CoordinateGenerator();

        int x = generator.generateRandomNumber(0, gridWidth - 1);
        int y = generator.generateRandomNumber(0, gridHeight - 1);

        System.out.println("COMPUTER to make a guess");
        System.out.printf("COMPUTER x guess: %d%n", x);
        System.out.printf("COMPUTER y guess: %d%n", y);

        boolean isAHit = this.playerShips.isHit(x, y);
        if (isAHit) 
        {
            System.out.println("COMPUTER HITTTTT !!!");
        } 
        else 
        {
            System.out.println("COMPUTER MISSSSS !!!");
        }
        return isAHit;
    }

    public boolean makePlayerGuess(int gridWidth, int gridHeight) 
    {
        Validation validation = new Validation();
        Input inputReader = new Input();

        int xPos = -1;
        int yPos = -1;
        String inputString = "";
        boolean isInvalidInput = true;

        System.out.println("PLAYER to make a guess");
        do 
        {
            System.out.printf("Ship x Position (%d - %d):%n", 0, gridWidth - 1);
            inputString = inputReader.readKeyboardInput();
            isInvalidInput = !validation.validateCoordinate(inputString, gridWidth);
            if (isInvalidInput) 
            {
                System.out.println("Invalid ship x position");
                continue;
            } 
            else 
            {
                xPos = Integer.parseInt(inputString);
            }

            System.out.printf("Ship y Position (%d - %d):%n", 0, gridHeight - 1);
            inputString = inputReader.readKeyboardInput();
            isInvalidInput = !validation.validateCoordinate(inputString, gridHeight);
            if (isInvalidInput) 
            {
                System.out.println("Invalid ship y position");
                continue;
            } 
            else 
            {
                yPos = Integer.parseInt(inputString);
            }
        } while (isInvalidInput);

        boolean isAHit = this.computerShips.isHit(xPos, yPos);

        if (isAHit) 
        {
            System.out.println("PLAYER HITTTTT !!!");
        } else 
        {
            System.out.println("PLAYER MISSSSS !!!");
        }

        return isAHit;
    }

    public void printGameBoard(int gridWidth, int gridHeight, boolean shouldShowShip, ShipList player) 
    {
        String board = "";
        for (int x = 0; x < gridWidth; x++) 
        {
            for (int y = 0; y < gridHeight; y++) 
            {
                String shipMarker = player.getShipMarkerAtCoordinate(x, y, shouldShowShip);
                board += shipMarker; // board = board + shipMarker
            }
            board += "\n";// board = board + "\n"
        }
        System.out.print(board);
    }

    public String[] loadSettingFile() 
    {
        String[] settings;
        boolean invalidFile = true;
        Validation validation = new Validation();
        do 
        {
            FileIO reader = new FileIO("gamesettings.txt");
            settings = reader.readFile();
            if (settings != null) 
            {
                if (settings.length == 4) //5
                {
                    invalidFile = !(validation.validateInteger(settings[0]) && validation.validateBoolean(settings[1])
                            && validation.validateBoolean(settings[2]) && validation.validateInteger(settings[3]));
                }
            }

            if (invalidFile) 
            {
                System.out.println("Input file is invalid");
            }

        } while (invalidFile);

        return settings;
    }

}