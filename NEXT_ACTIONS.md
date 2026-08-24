# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 10/10 (100.0%)
- **Function parity:** 85/133 matched (target 106) — 63.9%
- **Class/type parity:** 22/34 matched (target 40) — 64.7%
- **Combined symbol parity:** 107/167 matched (target 146) — 64.1%
- **Average inline-code cosine:** 0.31 (function body across 10 matched files)
- **Average documentation cosine:** 0.60 (doc text across 10 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 9 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. reactor

- **Target:** `reactor.Reactor`
- **Similarity:** 0.20
- **Dependents:** 1
- **Priority Score:** 1073608.0
- **Functions:** 19/25 matched
- **Missing functions:** `readable_owned`, `writable_owned`, `ready`, `poll`, `fmt`, `drop`
- **Types:** 10/11 matched (target 12)
- **Missing types:** `Output`

### 2. lib

- **Target:** `asyncio.Lib`
- **Similarity:** 0.18
- **Dependents:** 0
- **Priority Score:** 316308.2
- **Functions:** 29/57 matched (target 31)
- **Missing functions:** `drop`, `poll`, `poll_next`, `as_raw_fd`, `as_fd`, `try_from`, `as_raw_socket`, `as_socket`, `poll_read`, `poll_read_vectored`, `poll_write`, `poll_write_vectored`, `poll_flush`, `poll_close`, `bind`, `accept`, `incoming`, `connect`, `peek`, `recv_from`, `peek_from`, `send_to`, `recv`, `send`, `pair`, `unbound`, `setup_networking`, `convert_path_to_socket_address`
- **Types:** 3/6 matched (target 3)
- **Missing types:** `Output`, `Item`, `Error`

### 3. driver

- **Target:** `asyncio.Driver`
- **Similarity:** 0.05
- **Dependents:** 0
- **Priority Score:** 91109.5
- **Functions:** 2/9 matched (target 3)
- **Missing functions:** `unparker`, `main_loop`, `parker_and_waker`, `create`, `wake_by_ref`, `wake`, `drop`
- **Types:** 0/2 matched (target 1)
- **Missing types:** `BlockOnWaker`, `CallOnDrop`

### 4. os.kqueue

- **Target:** `os.Kqueue`
- **Similarity:** 0.19
- **Dependents:** 0
- **Priority Score:** 72208.1
- **Functions:** 10/14 matched (target 10)
- **Missing functions:** `as_raw_fd`, `as_fd`, `try_from`, `registration`
- **Types:** 5/8 matched (target 6)
- **Missing types:** `Error`, `Ready`, `Output`

### 5. os.windows

- **Target:** `os.Windows`
- **Similarity:** 0.17
- **Dependents:** 0
- **Priority Score:** 61508.3
- **Functions:** 8/11 matched (target 14)
- **Missing functions:** `as_raw_handle`, `as_handle`, `try_from`
- **Types:** 1/4 matched
- **Missing types:** `Error`, `Ready`, `Output`

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

- **Target:** `os.Os`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 0.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

