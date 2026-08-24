# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 10/23 (43.5%)
- **Function parity:** 85/202 matched (target 106) — 42.1%
- **Class/type parity:** 22/44 matched (target 40) — 50.0%
- **Combined symbol parity:** 107/246 matched (target 146) — 43.5%
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

- **Target:** `reactor.Reactor [PROVENANCE-FALLBACK]`
- **Similarity:** 0.20
- **Dependents:** 1
- **Priority Score:** 1073608.0
- **Functions:** 19/25 matched
- **Missing functions:** `readable_owned`, `writable_owned`, `ready`, `poll`, `fmt`, `drop`
- **Types:** 10/11 matched (target 12)
- **Missing types:** `Output`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `reactor.rs` vs expected `reactor.rs`
- **Proposed provenance header:** `// port-lint: source reactor.rs` (current: `// port-lint: source reactor.rs`)
- **Lint issues:** 1

### 2. lib

- **Target:** `asyncio.Lib [PROVENANCE-FALLBACK]`
- **Similarity:** 0.18
- **Dependents:** 0
- **Priority Score:** 316308.2
- **Functions:** 29/57 matched (target 31)
- **Missing functions:** `drop`, `poll`, `poll_next`, `as_raw_fd`, `as_fd`, `try_from`, `as_raw_socket`, `as_socket`, `poll_read`, `poll_read_vectored`, `poll_write`, `poll_write_vectored`, `poll_flush`, `poll_close`, `bind`, `accept`, `incoming`, `connect`, `peek`, `recv_from`, `peek_from`, `send_to`, `recv`, `send`, `pair`, `unbound`, `setup_networking`, `convert_path_to_socket_address`
- **Types:** 3/6 matched (target 3)
- **Missing types:** `Output`, `Item`, `Error`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Lint issues:** 1

### 3. driver

- **Target:** `asyncio.Driver [PROVENANCE-FALLBACK]`
- **Similarity:** 0.05
- **Dependents:** 0
- **Priority Score:** 91109.5
- **Functions:** 2/9 matched (target 3)
- **Missing functions:** `unparker`, `main_loop`, `parker_and_waker`, `create`, `wake_by_ref`, `wake`, `drop`
- **Types:** 0/2 matched (target 1)
- **Missing types:** `BlockOnWaker`, `CallOnDrop`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `driver.rs` vs expected `driver.rs`
- **Proposed provenance header:** `// port-lint: source driver.rs` (current: `// port-lint: source driver.rs`)
- **Lint issues:** 1

### 4. os.kqueue

- **Target:** `os.Kqueue [PROVENANCE-FALLBACK]`
- **Similarity:** 0.19
- **Dependents:** 0
- **Priority Score:** 72208.1
- **Functions:** 10/14 matched (target 10)
- **Missing functions:** `as_raw_fd`, `as_fd`, `try_from`, `registration`
- **Types:** 5/8 matched (target 6)
- **Missing types:** `Error`, `Ready`, `Output`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `os/kqueue.rs` vs expected `os/kqueue.rs`
- **Proposed provenance header:** `// port-lint: source os/kqueue.rs` (current: `// port-lint: source os/kqueue.rs`)
- **Lint issues:** 1

### 5. os.windows

- **Target:** `os.Windows [PROVENANCE-FALLBACK]`
- **Similarity:** 0.17
- **Dependents:** 0
- **Priority Score:** 61508.3
- **Functions:** 8/11 matched (target 14)
- **Missing functions:** `as_raw_handle`, `as_handle`, `try_from`
- **Types:** 1/4 matched
- **Missing types:** `Error`, `Ready`, `Output`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `os/windows.rs` vs expected `os/windows.rs`
- **Proposed provenance header:** `// port-lint: source os/windows.rs` (current: `// port-lint: source os/windows.rs`)
- **Lint issues:** 1

### 6. reactor.windows

- **Target:** `reactor.Windows [PROVENANCE-FALLBACK]`
- **Similarity:** 0.44
- **Dependents:** 0
- **Priority Score:** 705.6
- **Functions:** 6/6 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 4)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `reactor/windows.rs` vs expected `reactor/windows.rs`
- **Proposed provenance header:** `// port-lint: source reactor/windows.rs` (current: `// port-lint: source reactor/windows.rs`)
- **Lint issues:** 1

### 7. reactor.kqueue

- **Target:** `reactor.Kqueue [PROVENANCE-FALLBACK]`
- **Similarity:** 0.33
- **Dependents:** 0
- **Priority Score:** 606.7
- **Functions:** 5/5 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 5)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `reactor/kqueue.rs` vs expected `reactor/kqueue.rs`
- **Proposed provenance header:** `// port-lint: source reactor/kqueue.rs` (current: `// port-lint: source reactor/kqueue.rs`)
- **Lint issues:** 1

### 8. reactor.unix

- **Target:** `reactor.Unix [PROVENANCE-FALLBACK]`
- **Similarity:** 0.40
- **Dependents:** 0
- **Priority Score:** 606.0
- **Functions:** 5/5 matched (target 8)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `reactor/unix.rs` vs expected `reactor/unix.rs`
- **Proposed provenance header:** `// port-lint: source reactor/unix.rs` (current: `// port-lint: source reactor/unix.rs`)
- **Lint issues:** 1

### 9. os.unix

- **Target:** `os.Unix [PROVENANCE-FALLBACK]`
- **Similarity:** 0.13
- **Dependents:** 0
- **Priority Score:** 108.7
- **Functions:** 1/1 matched (target 4)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 2)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `os/unix.rs` vs expected `os/unix.rs`
- **Proposed provenance header:** `// port-lint: source os/unix.rs` (current: `// port-lint: source os/unix.rs`)
- **Lint issues:** 1

### 10. os

- **Target:** `os.Os [PROVENANCE-FALLBACK]`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 0.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `os.rs` vs expected `os.rs`
- **Proposed provenance header:** `// port-lint: source os.rs` (current: `// port-lint: source os.rs`)
- **Lint issues:** 1

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

