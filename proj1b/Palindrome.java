public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque q = new LinkedListDeque();
        for (char c : word.toCharArray()) {
            q.addLast(c);
        }
        return q;
    }

    public boolean isPalindrome(String word) {
        Deque q = wordToDeque(word);
        return isPalindrome(q);
    }

    private boolean isPalindrome(Deque q) {
        if (q.size() == 0 || q.size() == 1) {
            return true;
        }
        Object f = q.removeFirst();
        Object l = q.removeLast();
        return (f == l) && isPalindrome(q);
    }

    public boolean isPalindromeNoRecursion(String word) {
        Deque q = wordToDeque(word);
        while (q.size() > 0) {
            Object f = q.removeFirst();
            if (q.isEmpty()) {
                return true;
            }
            Object l = q.removeLast();
            if (f != l) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindromeNoDeque(String word) {
        for (int i = 0, j = word.length() - 1 - i;
                 i < j;
                 i++, j--) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque q = wordToDeque(word);
        return isPalindrome(q, cc);
    }

    private boolean isPalindrome(Deque<Character> q, CharacterComparator cc) {
        if (q.size() == 0 || q.size() == 1) {
            return true;
        }
        char f = q.removeFirst();
        char l = q.removeLast();
        return cc.equalChars(f, l) && isPalindrome(q, cc);
    }
}
