package Game;

public class Parser {
	private static ScriptExpression result;

	//returns null, if no commands parsed
	public static ScriptExpression parse(String string){
		result = null;
		String command;
		int oldIndex = -1;
		int index;
		
		int firstBracketIndex;
		int secondBracketIndex;
		
		String argString;
		int arg;
		
		for(;;){
			index = string.indexOf(";", oldIndex + 1);
			if(index != -1){
				command = string.substring(oldIndex + 1, index).trim();
				oldIndex = index;
				
				firstBracketIndex = command.indexOf("(");
				secondBracketIndex = command.indexOf(")");
				
				argString = command.substring(firstBracketIndex + 1, secondBracketIndex).trim();
				arg = Integer.parseInt(argString);
				
				command = command.substring(0, firstBracketIndex);
				
				switch (command){
				case "вверх":
					addExpression(new UpCommand(arg));
					break;
				case "вниз":
					addExpression(new DownCommand(arg));
					break;
				case "влево":
					addExpression(new LeftCommand(arg));
					break;
				case "вправо":
					addExpression(new RightCommand(arg));
					break;
				default:
					break;
				}
			}
			else break;
		}
		return result;
	}
	
	/*
	public void start(Ball ball){
		if(result == null){
			//TODO: вывод ошибки
		}
		else{
			result.interpret(ball);
		}
	}
	*/
	
	//добавляет выражение к текущему (справа)
	private static void addExpression(ScriptExpression toAdd){
		if(result == null){
			result = toAdd;
		}
		else{
			result = new SequenceExpression(result, toAdd);
		}
	}
}