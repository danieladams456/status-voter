package com.github.danieladams456.statusvoter;

import java.util.List;

public class TestData {
    static final List<StatusClassification> STATUSES_IN_ORDER = List.of(
            StatusClassification.INITIAL,
            StatusClassification.SUCCESS,
            StatusClassification.UNCLEAR_ATTRIBUTION_ERROR,
            StatusClassification.CUSTOMER_DATA_ERROR,
            StatusClassification.CUSTOMER_DATA_VALIDATION_ERROR,
            StatusClassification.INTERNAL_STATUS_MERGE_ERROR,
            StatusClassification.INTERNAL_ERROR
    );
}
