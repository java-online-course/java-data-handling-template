package com.epam.izh.rd.online.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class SieveEratosthenes {

    private static class PrimePair {
        BigInteger prime;
        BigInteger lastCrossed;

        PrimePair(BigInteger prime, BigInteger lastCrossed) {
            this.prime = prime;
            this.lastCrossed = lastCrossed;
        }
    }

    private List<PrimePair> primes;

    SieveEratosthenes() {
        primes = new ArrayList<>();
        primes.add(new PrimePair(new BigInteger("2"), new BigInteger("2")));
        primes.add(new PrimePair(new BigInteger("3"), new BigInteger("3")));
    }

    public BigInteger fillNPrimes(int n) {
        if (n >= 0 && n <= 1) {
            return primes.get(n).prime;
        }
        while (primes.size() <= n) {
            addNextPrime();
        }
        return primes.get(primes.size() - 1).prime;
    }

    private void addNextPrime() {
        BigInteger candidate = primes.get(primes.size() - 1).prime.add(new BigInteger("2"));
        for (int i = 1; i < primes.size(); i++) {
            PrimePair p = primes.get(i);
            while (p.lastCrossed.compareTo(candidate) < 0) {
                p.lastCrossed = p.lastCrossed.add(p.prime);
            }
            if (p.lastCrossed.compareTo(candidate) == 0) {
                candidate = candidate.add(new BigInteger("2"));
                i = -1;
            }
        }
        primes.add(new PrimePair(candidate, candidate));
    }
}

