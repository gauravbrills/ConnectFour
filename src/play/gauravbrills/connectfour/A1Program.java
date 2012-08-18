package play.gauravbrills.connectfour;

/*
 * Description of program, name of author, date
 *
 */
public class A1Program {

	static final char WS = ' ';
	static final String WINFORPLAYER[] = { "XXXX", "OOOO" };
	static final char token[] = { 'X', 'O' };
	private String input = "                                          ";
	private boolean gameEnd = false;
	private int userInputs[] = new int[2];

	public void start() {
		displayConnectFourBoard(input);
		while (!gameEnd) {
			for (int i = 0; i < 2; i++) {
				StringBuilder rowString = new StringBuilder();
				userInputs[i] = getInputFromPlayer(i);
				if (userInputs[i] >= 1 & userInputs[i] <= 7) {
					rowString.append(getColumnString(input, userInputs[i]));
					if (rowString.toString().trim().length() < 6) {
						rowString = new StringBuilder(padWithWS(rowString
								.toString().trim() + token[i]));
						input = mergeRowWithBoard(input, rowString.toString(),
								userInputs[i]);
						displayConnectFourBoard(input);
						gameEnd = checkForWin(i + 1);
						if (gameEnd == true) {
							break;
						}
					}
				}
			}
		}
	}

	private void displayConnectFourBoard(String input) {
		for (int i = 0; i < 6; i++) {
			displayConectFourRow(input, i, '|');
		}
	}

	private void displayConectFourRow(String input, int rowid, char c) {
		StringBuilder output = new StringBuilder();
		for (int i = (5 - rowid); i < 42 - rowid; i += 6) {
			output.append(c).append(input.charAt(i));
		}
		System.out.println(output.append(c));
	}

	private String getColumnString(String input, int index) {
		String rowString;
		rowString = input.substring((index - 1) * 6, index * 6).trim();
		return rowString;
	}

	private String mergeRowWithBoard(String input, String rowString,
			Integer player1Input) {
		String out = input.substring(0, (player1Input - 1) * 6) + rowString
				+ input.substring(player1Input * 6, 42);
		return out;
	}

	private String padWithWS(String str) {
		StringBuilder strBldr = new StringBuilder(str);
		for (int i = 0; i < 6 - str.length(); i++) {
			strBldr.append(WS);
		}
		return strBldr.toString();
	}

	private Integer getInputFromPlayer(int i) {
		int chance = 0;
		System.out.println("Player " + (i + 1) + " Enter column for "
				+ token[i]);
		chance = Integer.parseInt(Keyboard.readInput());
		return chance;
	}

	private boolean checkForWin(int playerid) {
		boolean gameOver = false;
		for (int i = 1; i < 8; i++) {
			String column = getColumnString(input, i);
			if (column.indexOf(WINFORPLAYER[playerid - 1]) != -1) {
				System.out.println("Player " + playerid + " Wins :) ");
				gameOver = true;
				break;
			}
		}
		return gameOver;
	}
}