package Game;

public class Parser {
	private ScriptExpression expr;
	
	public void parse(String string){
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
				case "�����":
					addExpression(new UpCommand(arg));
					break;
				case "����":
					addExpression(new DownCommand(arg));
					break;
				case "�����":
					addExpression(new LeftCommand(arg));
					break;
				case "������":
					addExpression(new RightCommand(arg));
					break;
				default:
					break;
				}
			}
			else break;
		}
		
	}
	
	public void start(Ball ball){
		if(expr == null){
			//TODO: ����� ������
		}
		else{
			expr.interpret(ball);
		}
	}
	
	//��������� ��������� � �������� (������)
	private void addExpression(ScriptExpression toAdd){
		if(expr == null){
			expr = toAdd;
		}
		else{
			expr = new SequenceExpression(expr, toAdd);
		}
	}
}