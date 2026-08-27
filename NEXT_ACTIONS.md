# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 10/10 (100.0%)
- **Function parity:** 133/133 matched (target 180) — 100.0%
- **Class/type parity:** 34/34 matched (target 55) — 100.0%
- **Combined symbol parity:** 167/167 matched (target 235) — 100.0%
- **Average inline-code cosine:** 0.28 (function body across 10 matched files)
- **Average documentation cosine:** 0.59 (doc text across 10 matched files)
- **Cheat-zeroed Files:** 1
- **Critical Issues:** 10 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. reactor

- **Target:** `reactor.Reactor`
- **Similarity:** 0.33
- **Dependents:** 1
- **Priority Score:** 1003606.7
- **Functions:** 25/25 matched (target 49)
- **Missing functions:** _none_
- **Types:** 11/11 matched (target 15)
- **Missing types:** _none_

### 2. lib

- **Target:** `asyncio.Lib`
- **Similarity:** 0.29
- **Dependents:** 0
- **Priority Score:** 6307.1
- **Functions:** 57/57 matched (target 62)
- **Missing functions:** _none_
- **Types:** 6/6 matched
- **Missing types:** _none_

### 3. os.kqueue

- **Target:** `os.Kqueue`
- **Similarity:** 0.28
- **Dependents:** 0
- **Priority Score:** 2207.2
- **Functions:** 14/14 matched (target 16)
- **Missing functions:** _none_
- **Types:** 8/8 matched (target 9)
- **Missing types:** _none_

### 4. os.windows

- **Target:** `os.Windows`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 1507.4
- **Functions:** 11/11 matched (target 17)
- **Missing functions:** _none_
- **Types:** 4/4 matched (target 7)
- **Missing types:** _none_

### 5. driver

- **Target:** `asyncio.Driver`
- **Similarity:** 0.34
- **Dependents:** 0
- **Priority Score:** 1106.6
- **Functions:** 9/9 matched (target 10)
- **Missing functions:** _none_
- **Types:** 2/2 matched (target 3)
- **Missing types:** _none_

### 6. reactor.windows

- **Target:** `reactor.Windows`
- **Similarity:** 0.44
- **Dependents:** 0
- **Priority Score:** 705.6
- **Functions:** 6/6 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 4)
- **Missing types:** _none_

### 7. reactor.kqueue

- **Target:** `reactor.Kqueue`
- **Similarity:** 0.33
- **Dependents:** 0
- **Priority Score:** 606.7
- **Functions:** 5/5 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 5)
- **Missing types:** _none_

### 8. reactor.unix

- **Target:** `reactor.Unix`
- **Similarity:** 0.40
- **Dependents:** 0
- **Priority Score:** 606.0
- **Functions:** 5/5 matched (target 8)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_

### 9. os.unix

- **Target:** `os.Unix`
- **Similarity:** 0.13
- **Dependents:** 0
- **Priority Score:** 108.7
- **Functions:** 1/1 matched (target 4)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 2)
- **Missing types:** _none_

### 10. os

- **Target:** `os.Os [ZERO]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched (target 3)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 2)
- **Missing types:** _none_

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

