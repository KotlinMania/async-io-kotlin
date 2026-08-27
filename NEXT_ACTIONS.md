# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 10/23 (43.5%)
- **Function parity:** 89/202 matched (target 131) — 44.1%
- **Class/type parity:** 22/44 matched (target 42) — 50.0%
- **Combined symbol parity:** 111/246 matched (target 173) — 45.1%
- **Average inline-code cosine:** 0.23 (function body across 9 matched files)
- **Average documentation cosine:** 0.63 (doc text across 9 matched files)
- **Cheat-zeroed Files:** 2
- **Critical Issues:** 10 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. async-io.reactor

- **Target:** `reactor.Reactor`
- **Similarity:** 0.32
- **Dependents:** 1
- **Priority Score:** 1033606.8
- **Functions:** 23/25 matched (target 47)
- **Missing functions:** `fmt`, `drop`
- **Types:** 10/11 matched (target 13)
- **Missing types:** `Output`

### 2. async-io.lib

- **Target:** `asyncio.Lib [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 316310.0
- **Functions:** 29/57 matched (target 31)
- **Missing functions:** `drop`, `poll`, `poll_next`, `as_raw_fd`, `as_fd`, `try_from`, `as_raw_socket`, `as_socket`, `poll_read`, `poll_read_vectored`, `poll_write`, `poll_write_vectored`, `poll_flush`, `poll_close`, `bind`, `accept`, `incoming`, `connect`, `peek`, `recv_from`, `peek_from`, `send_to`, `recv`, `send`, `pair`, `unbound`, `setup_networking`, `convert_path_to_socket_address`
- **Types:** 3/6 matched (target 3)
- **Missing types:** `Output`, `Item`, `Error`

### 3. async-io.driver

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

### 10. async-io.os

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

