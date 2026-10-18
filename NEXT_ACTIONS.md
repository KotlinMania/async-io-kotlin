# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 8/23 (34.8%)
- **Function parity:** 76/145 matched (target 115) — 52.4%
- **Class/type parity:** 28/38 matched (target 47) — 73.7%
- **Combined symbol parity:** 104/183 matched (target 162) — 56.8%
- **Average inline-code cosine:** 0.31 (function body across 8 matched files)
- **Average documentation cosine:** 0.61 (doc text across 8 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 8 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. async-io.reactor

- **Target:** `reactor.Reactor [PROVENANCE-FALLBACK]`
- **Similarity:** 0.33
- **Dependents:** 1
- **Priority Score:** 1003606.7
- **Functions:** 25/25 matched (target 49)
- **Missing functions:** _none_
- **Types:** 11/11 matched (target 15)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `reactor.rs` vs expected `reactor.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:reactor.rs` vs expected `reactor.rs`
- **Proposed provenance header:** `// port-lint: source reactor.rs` (current: `// port-lint: source reactor.rs`)
- **Proposed provenance header:** `// port-lint: tests reactor.rs` (current: `// port-lint: tests reactor.rs`)
- **Lint issues:** 2

### 2. os.kqueue

- **Target:** `os.Kqueue [PROVENANCE-FALLBACK]`
- **Similarity:** 0.28
- **Dependents:** 0
- **Priority Score:** 2207.2
- **Functions:** 14/14 matched (target 16)
- **Missing functions:** _none_
- **Types:** 8/8 matched (target 9)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `os/kqueue.rs` vs expected `os/kqueue.rs`
- **Proposed provenance header:** `// port-lint: source os/kqueue.rs` (current: `// port-lint: source os/kqueue.rs`)
- **Lint issues:** 1

### 3. os.windows

- **Target:** `os.Windows [PROVENANCE-FALLBACK]`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 1507.4
- **Functions:** 11/11 matched (target 17)
- **Missing functions:** _none_
- **Types:** 4/4 matched (target 7)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `os/windows.rs` vs expected `os/windows.rs`
- **Proposed provenance header:** `// port-lint: source os/windows.rs` (current: `// port-lint: source os/windows.rs`)
- **Lint issues:** 1

### 4. async-io.driver

- **Target:** `asyncio.Driver [PROVENANCE-FALLBACK]`
- **Similarity:** 0.34
- **Dependents:** 0
- **Priority Score:** 1106.6
- **Functions:** 9/9 matched (target 10)
- **Missing functions:** _none_
- **Types:** 2/2 matched (target 3)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `driver.rs` vs expected `driver.rs`
- **Proposed provenance header:** `// port-lint: source driver.rs` (current: `// port-lint: source driver.rs`)
- **Lint issues:** 1

### 5. reactor.windows

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

### 6. reactor.kqueue

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

### 7. reactor.unix

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

### 8. os.unix

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

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

## Reexport / Wiring Modules

These files match `reexport_modules` patterns in `.ast_distance_config.json`. They are filtered out of
normal priority and missing-file ladders because they are wiring
modules, not direct logic ports. Consult them for call-site routing;
do not treat them as the next implementation target by default.

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `async-io.lib` | `asyncio.src.Lib` | 0 | `async-io/src/lib.rs` | `asyncio/src/Lib.kt` |
| `async-io.os` | `asyncio.src.os.Os` | 0 | `async-io/src/os.rs` | `asyncio/src/os/Os.kt` |

