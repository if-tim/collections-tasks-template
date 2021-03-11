package tasks.first;

import java.util.ArrayDeque;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        boolean[] chosenNodes = new boolean[adjacencyMatrix.length];
        chosenNodes[startIndex] = true;
        Integer startNode = startIndex;
        ArrayDeque<Integer> arrayDeque = new ArrayDeque();
        arrayDeque.offer(startNode);
        String result = "" + startNode;
        while(!arrayDeque.isEmpty()) {
            for (int i = 0, nodeIndex = arrayDeque.getFirst(); i < adjacencyMatrix[nodeIndex].length; i++) {
                if (adjacencyMatrix[nodeIndex][i] && !chosenNodes[i]) {
                    arrayDeque.offer(i);
                    chosenNodes[i] = true;
                    result += ", " + i;
                }
            }
            arrayDeque.remove();
        }
        return result;
    }

    @Override
    public Boolean validateBrackets(String s) {
        ArrayDeque<Character> arrayDeque = new ArrayDeque<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char currentSymbol = charArray[i];
            if (currentSymbol == ')' || currentSymbol == ']' || currentSymbol == '}') {
                char oppositeBracket = ' ';
                switch (currentSymbol) {
                    case ')':
                        oppositeBracket = '(';
                        break;
                    case ']':
                        oppositeBracket = '[';
                        break;
                    case '}':
                        oppositeBracket = '{';
                }
                if (oppositeBracket == arrayDeque.getFirst()) {
                    arrayDeque.removeFirst();
                }
            }
            if (currentSymbol == '(' || currentSymbol == '[' || currentSymbol == '{') {
                arrayDeque.offerFirst(currentSymbol);
            }
        }
        return arrayDeque.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
        char[] charArray = s.toCharArray();
        ArrayDeque<Long> arrayDequeOfNumbers = new ArrayDeque<>();
        ArrayDeque<Character> arrayDequeOfOperations = new ArrayDeque<>();
        int i = 0;
        while (charArray[i] != '+' && charArray[i] != '-' && charArray[i] != '*' && charArray[i] != '/') {
            arrayDequeOfNumbers.offerFirst(Long.parseLong(String.valueOf(charArray[i])));
            i += 2;
        }
        for (int j = i; j < charArray.length; j += 2) {
            arrayDequeOfOperations.offer(charArray[j]);
        }
        Long result = 0L;
        while (arrayDequeOfNumbers.size() != 1) {
            Long tmp = 0L;
            switch (arrayDequeOfOperations.getFirst()) {
                case '+':
                    tmp = arrayDequeOfNumbers.getFirst();
                    arrayDequeOfNumbers.removeFirst();
                    result = tmp + arrayDequeOfNumbers.getFirst();
                    arrayDequeOfNumbers.removeFirst();
                    arrayDequeOfNumbers.offerFirst(result);
                    arrayDequeOfOperations.removeFirst();
                    break;
                case '-':
                    tmp = arrayDequeOfNumbers.getFirst();
                    arrayDequeOfNumbers.removeFirst();
                    result = arrayDequeOfNumbers.getFirst() - tmp;
                    arrayDequeOfNumbers.removeFirst();
                    arrayDequeOfNumbers.offerFirst(result);
                    arrayDequeOfOperations.removeFirst();
                    break;
                case '*':
                    tmp = arrayDequeOfNumbers.getFirst();
                    arrayDequeOfNumbers.removeFirst();
                    result = tmp * arrayDequeOfNumbers.getFirst();
                    arrayDequeOfNumbers.removeFirst();
                    arrayDequeOfNumbers.offerFirst(result);
                    arrayDequeOfOperations.removeFirst();
                    break;
                case '/':
                    tmp = arrayDequeOfNumbers.getFirst();
                    arrayDequeOfNumbers.removeFirst();
                    result = arrayDequeOfNumbers.getFirst() / tmp;
                    arrayDequeOfNumbers.removeFirst();
                    arrayDequeOfNumbers.offerFirst(result);
                    arrayDequeOfOperations.removeFirst();
                    break;
            }
        }
        return result;
    }
}
