# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 10/10 (100.0%)
- **Function parity:** 55/133 matched (target 71) — 41.4%
- **Class/type parity:** 15/34 matched (target 28) — 44.1%
- **Combined symbol parity:** 70/167 matched (target 99) — 41.9%
- **Average inline-code cosine:** 0.19 (function body across 9 matched files)
- **Average documentation cosine:** 0.60 (doc text across 9 matched files)
- **Cheat-zeroed Files:** 0
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
- **Similarity:** 0.05
- **Dependents:** 1
- **Priority Score:** 1263609.5
- **Functions:** 6/25 matched (target 8)
- **Missing functions:** `insert_io`, `remove_io`, `notify`, `lock`, `try_lock`, `process_timers`, `process_timer_ops`, `react`, `is_empty`, `drain_into`, `poll_readable`, `poll_writable`, `poll_ready`, `readable_owned`, `writable_owned`, `ready`, `poll`, `fmt`, `drop`
- **Types:** 4/11 matched (target 4)
- **Missing types:** `ReactorLock`, `TimerOp`, `Direction`, `Output`, `ReadableOwned`, `WritableOwned`, `Ready`

### 2. lib

- **Target:** `asyncio.Lib`
- **Similarity:** 0.14
- **Dependents:** 0
- **Priority Score:** 406308.6
- **Functions:** 20/57 matched (target 22)
- **Missing functions:** `drop`, `poll`, `poll_next`, `as_raw_fd`, `as_fd`, `try_from`, `as_raw_socket`, `as_socket`, `readable_owned`, `writable_owned`, `poll_readable`, `poll_writable`, `read_with_mut`, `write_with_mut`, `as_ref`, `poll_read`, `poll_read_vectored`, `poll_write`, `poll_write_vectored`, `poll_flush`, `poll_close`, `bind`, `accept`, `incoming`, `connect`, `peek`, `recv_from`, `peek_from`, `send_to`, `recv`, `send`, `pair`, `unbound`, `optimistic`, `setup_networking`, `set_nonblocking`, `convert_path_to_socket_address`
- **Types:** 3/6 matched (target 3)
- **Missing types:** `Output`, `Item`, `Error`

### 3. os.kqueue

- **Target:** `os.Kqueue`
- **Similarity:** 0.14
- **Dependents:** 0
- **Priority Score:** 122208.6
- **Functions:** 6/14 matched (target 6)
- **Missing functions:** `as_ref`, `as_mut`, `as_raw_fd`, `as_fd`, `try_from`, `poll_ready`, `poll`, `registration`
- **Types:** 4/8 matched (target 5)
- **Missing types:** `Error`, `Ready`, `Output`, `QueueableSealed`

### 4. os.windows

- **Target:** `os.Windows`
- **Similarity:** 0.11
- **Dependents:** 0
- **Priority Score:** 101508.9
- **Functions:** 4/11 matched (target 10)
- **Missing functions:** `as_ref`, `as_raw_handle`, `as_handle`, `try_from`, `get_mut`, `poll_ready`, `poll`
- **Types:** 1/4 matched
- **Missing types:** `Error`, `Ready`, `Output`

### 5. driver

- **Target:** `asyncio.Driver`
- **Similarity:** 0.06
- **Dependents:** 0
- **Priority Score:** 91109.4
- **Functions:** 2/9 matched (target 2)
- **Missing functions:** `unparker`, `main_loop`, `parker_and_waker`, `create`, `wake_by_ref`, `wake`, `drop`
- **Types:** 0/2 matched (target 1)
- **Missing types:** `BlockOnWaker`, `CallOnDrop`

### 6. reactor.windows

- **Target:** `reactor.Windows`
- **Similarity:** 0.41
- **Dependents:** 0
- **Priority Score:** 705.9
- **Functions:** 6/6 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 3)
- **Missing types:** _none_
- **Lint issues:** 1

### 7. reactor.kqueue

- **Target:** `reactor.Kqueue`
- **Similarity:** 0.30
- **Dependents:** 0
- **Priority Score:** 607.0
- **Functions:** 5/5 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 4)
- **Missing types:** _none_
- **Lint issues:** 1

### 8. reactor.unix

- **Target:** `reactor.Unix`
- **Similarity:** 0.40
- **Dependents:** 0
- **Priority Score:** 606.0
- **Functions:** 5/5 matched (target 8)
- **Missing functions:** _none_
- **Types:** 1/1 matched
- **Missing types:** _none_
- **Lint issues:** 1

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

- **Target:** `os.Os [STUB]`
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

