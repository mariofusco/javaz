package org.javaz.trampoline;

import java.util.function.*;

import static java.lang.Character.*;
import static org.javaz.trampoline.TailCalls.*;

public class PalindromePredicate implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return isPalindromeTrampoline(s, 0, s.length()-1).invoke();
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end && !isLetter(s.charAt(start))) start++;
        while (start < end && !isLetter(s.charAt(end))) end--;
        if (start >= end) return true;
        if (toLowerCase(s.charAt(start)) != toLowerCase(s.charAt(end))) return false;
        return isPalindrome(s, start+1, end-1);
    }

    private TailCall<Boolean> isPalindromeTrampoline(String s, int start, int end) {
        while (start < end && !isLetter(s.charAt(start))) start++;
        while (end > start && !isLetter(s.charAt(end))) end--;
        if (start >= end) return done(true);
        if (toLowerCase(s.charAt(start)) != toLowerCase(s.charAt(end))) return done(false);
        int newStart = start + 1;
        int newEnd = end - 1;
        return call(() -> isPalindromeTrampoline(s, newStart, newEnd));
    }
}
