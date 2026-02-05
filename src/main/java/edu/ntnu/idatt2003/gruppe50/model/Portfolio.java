package edu.ntnu.idatt2003.gruppe50.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a portfolio containing shares owned by a user.
 * <br>
 * The portfolio maintains a collection of {@link Share} objects.
 */
public class Portfolio {
    private final List<Share> shares;

    /**
     * Creates a new {@code Portfolio} with an empty list.
     */
    public Portfolio() {
        this.shares = new ArrayList<>();
    }

    /**
     * Adds a new share to the portfolio.
     *
     * @param share The new share
     * @return {@code true} if the share was added successfully,
     *           {@code false} if the share could not be added
     */
    public boolean addShare(Share share) {
        if (share == null) return false;
        return shares.add(share);
    }

    /**
     * Removes a  share from the portfolio.
     *
     * @param share The share to remove
     * @return {@code true} if the share was removed successfully,
     *           {@code false} if the share could not be removed
     */
    public boolean removeShare(Share share) {
        if (share == null) return false;
        return shares.remove(share);
    }

    /**
     * Returns all shares in the portfolio.
     *
     * @return an unmodifiable list of the shares in this portfolio
     */
    public List<Share> getShares() {
        return List.copyOf(shares);
    }


    /**
     * Returns all shares in the portfolio with the given symbol.
     *
     * @param symbol the share symbol to filter by
     * @return a list of all shares with the given symbol,
     *         or an empty list if none are found
     * @throws IllegalArgumentException if symbol is null
     */
    public List<Share> getShares(String symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException("Symbol cannot be null");
        }
        return shares.stream()
                .filter(share -> share.getStock().getSymbol().equalsIgnoreCase(symbol))
                .collect(Collectors.toList());
        //vurdere å beskrive dette i rapporten!!
        //Spesielt med tanke på at den går i share for å hente stock for å hente symbol
    }

    /**
     * Checks whether the portfolio contains the given share
     *
     * @param shareToCheck the share to check for
     * @return {@code true} if the portfolio contains the share,
     *         {@code false} otherwise
     */
    public boolean contains(Share shareToCheck) {
        return shares.stream()
                .anyMatch(share ->
                        share.getStock().equals(shareToCheck.getStock()));
        }
}
