package com.sarvatra.dto;

import com.sarvatra.entities.type.BloodGroupType;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class BloodGroupStats {
    private final BloodGroupType bloodGroupType;
    private final Long count;
}

