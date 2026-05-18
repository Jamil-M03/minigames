# Minigames

> A console-based Java arcade. Four games, one engine, my first real OOP project.

A terminal-based collection of four classic games — Connect Four,
Connect Three, Tic-Tac-Toe, and Hangman — built in Java around a shared
abstract game engine. Players take turns through a unified main menu,
with a persistent leaderboard, an optional AI opponent, and colorized
terminal output.

This was my first substantial programming project, written during my
first semester at the American University of Beirut (Fall 2024). It's preserved here as a record of where I started and how
much I bit off as a beginner.

---

## What's inside

| Game | Players | Notes |
|---|---|---|
| **Connect Three** | 1–2 | A simplified 4×4 Connect-style game; gentler than Connect Four |
| **Connect Four** | 1–2 | Standard 6×7 board, gravity-based piece drops, four-in-a-row diagonals included |
| **Tic-Tac-Toe** | 1–2 | The classic 3×3 grid |
| **Hangman** | 1 | Solo word-guessing game with a hand-drawn noose, word bank built into the project |

All games support an optional **AI opponent** that picks random legal
moves. Multi-game sessions track scores across rounds via a shared
`Leaderboard`.

## Architecture

The interesting part isn't any individual game — it's the engine they
share. The same skeleton drives all four, with each concrete game
filling in the abstract methods:

```
                     ┌──────────────────┐
                     │   AbstractGame   │  game loop, turn rotation,
                     │   (abstract)     │  win/draw checks, menu hooks
                     └────────┬─────────┘
                              │
        ┌───────────┬─────────┼─────────┬─────────────┐
        ▼           ▼         ▼         ▼             ▼
   ConnectThree  ConnectFour  TicTacToe Hangman   (future games)
   (4×4 board)   (6×7 board)  (3×3)     (word)

                     ┌──────────────────┐
                     │     Player       │  name, score, getInput()
                     │   (abstract)     │
                     └────────┬─────────┘
                              │
                    ┌─────────┴─────────┐
                    ▼                   ▼
              HumanPlayer            AIPlayer
            (Scanner input)       (Random moves)
```

Other classes split responsibilities:

- **`Grid`** — generic 2D character grid used by every board game
- **`WinChecker`** — pure-logic class for detecting 3-in-a-row and 4-in-a-row across rows, columns, and both diagonals
- **`Leaderboard`** — sorts and renders the per-session high-score table
- **`Designer`** — handles all ASCII art, terminal clearing, and menu rendering
- **`StringColorer` / `Colors`** — ANSI escape-code wrapper for colored terminal output
- **`WordBank`** — Hangman's dictionary, with random-word selection
- **`Noose`** — Hangman's evolving stick-figure drawing, advanced each wrong guess
- **`GameFactory`** — registry-based factory using Java reflection to instantiate games by ID

## What I learned building this

This project was where the concepts from intro OOP stopped being abstract textbook ideas:

- **Inheritance and polymorphism** stopped being terminology and became "how do I make the same game loop work for four different games?"
- **Abstract classes** clicked when I needed `AbstractGame.startGame()` to drive sub-classes I hadn't written yet
- **The Factory pattern** came in when adding a fifth game made the `if (gameNumber == 1)` chain in `main` feel obviously wrong
- **Separation of concerns** revealed itself when I tried to add color to the output and realized the rendering logic was scattered across every file — leading to the refactor into `Designer` and `StringColorer`
- **Java reflection** showed up when I wanted `GameFactory` to instantiate games by string name without hard-coded constructors

The TODOs scattered through the code and the commented-out experiments
are honest artifacts of that learning process. I've left them in.

## Running it

### Prerequisites

- Java 11 or higher (`java -version` to check)
- A terminal that supports ANSI color codes (most modern terminals do; on Windows, PowerShell 7+ or Windows Terminal works)

### From the command line

```bash
git clone https://github.com/Jamil-M03/minigames.git
cd minigames/Minigames
javac BoardGames/*.java
java BoardGames.BoardGameApp
```

### From an IDE

Open the `Minigames` folder as a project in IntelliJ IDEA (or any
Java IDE). The main entry point is `BoardGames.BoardGameApp`. Run it
and pick a game from the menu.

## Project layout

```
Minigames/
└── BoardGames/
    ├── BoardGameApp.java       main entry point, top-level menu
    ├── AbstractGame.java       abstract base for all games
    ├── ConnectFour.java        Connect Four (6×7)
    ├── ConnectThree.java       Connect Three (4×4)
    ├── TicTacToe.java          Tic-Tac-Toe (3×3)
    ├── Hangman.java            Hangman with ASCII noose
    ├── HangmanWord.java        guessed-letter state for the puzzle
    ├── HangmanChar.java        per-character validation rules
    ├── WordBank.java           dictionary + random-word picker
    ├── Noose.java              evolving ASCII stick figure
    ├── Player.java             abstract player base
    ├── HumanPlayer.java        Scanner-driven human input
    ├── AIPlayer.java           random-move bot
    ├── GameFactory.java        registry-based reflection factory
    ├── Grid.java               generic 2D character grid
    ├── WinChecker.java         pure-function win detection
    ├── Leaderboard.java        score tracking across rounds
    ├── Designer.java           ASCII art, menus, terminal control
    ├── StringColorer.java      ANSI color wrapping
    └── Colors.java             ANSI color constants
```

## Honest limitations

A first-semester project has first-semester limitations:

- **The AI is purely random.** No minimax, no heuristics — it picks legal moves uniformly. That was the goal at the time; smarter bots came later (see my [Connect 4 in C](https://github.com/SamiAllakkis/connect4-c) project for a Minimax with alpha-beta pruning).
- **No persistence between sessions.** The leaderboard resets every run.
- **No multiplayer over a network.** Local turn-based only. (Solved later in the C project above.)
- **The `GameFactory` exists but isn't fully wired in** — `BoardGameApp` still uses `if (gameNumber == 1)` branches. I knew the cleanup was needed; I shipped before doing it.
- **Hangman only supports one player at a time.** Multi-player Hangman would need a separate word-hiding flow.

These are the kinds of things you notice in your own old code two years later — and they're exactly the kind of "what would you do differently?" answers a good interviewer wants to hear.

---

© 2026 Jamil M. Licensed under MIT.
