package com.github.danieladams456.statusvoter;

/**
 * For now, using ordinal() value.
 * This depends on order of definitions.  Might change in the future to explicitly specify.
 */
public enum StatusClassification {
    INITIAL,
    SUCCESS,
    UNCLEAR_ATTRIBUTION_ERROR,
    CUSTOMER_DATA_ERROR,
    CUSTOMER_DATA_VALIDATION_ERROR,
    UNRECOGNIZED_STATUS_ERROR,
    INTERNAL_ERROR,
}
