# port-lint Proposed Changes

**Generated:** 2026-09-01
**Source:** tmp/async-io
**Target:** src/commonMain/kotlin/io/github/kotlinmania/asyncio

These are review proposals only. They are emitted when a Rust -> Kotlin pair matches only after fallback normalization, so the existing `port-lint` header is not an exact provenance match.

| Target file | Current header | Proposed header | Source path | Reason |
|-------------|----------------|-----------------|-------------|--------|
| `src/commonMain/kotlin/io/github/kotlinmania/asyncio/reactor/Reactor.kt` | `// port-lint: source reactor.rs` | `// port-lint: source reactor.rs` | `reactor.rs` | `port-lint provenance header matched only after fallback normalization: 'reactor.rs' vs expected 'reactor.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/asyncio/reactor/ReactorTest.kt` | `// port-lint: tests reactor.rs` | `// port-lint: tests reactor.rs` | `reactor.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:reactor.rs' vs expected 'reactor.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/asyncio/Lib.kt` | `// port-lint: source lib.rs` | `// port-lint: source lib.rs` | `lib.rs` | `port-lint provenance header matched only after fallback normalization: 'lib.rs' vs expected 'lib.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/asyncio/os/Kqueue.kt` | `// port-lint: source os/kqueue.rs` | `// port-lint: source os/kqueue.rs` | `os/kqueue.rs` | `port-lint provenance header matched only after fallback normalization: 'os/kqueue.rs' vs expected 'os/kqueue.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/asyncio/os/Windows.kt` | `// port-lint: source os/windows.rs` | `// port-lint: source os/windows.rs` | `os/windows.rs` | `port-lint provenance header matched only after fallback normalization: 'os/windows.rs' vs expected 'os/windows.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/asyncio/Driver.kt` | `// port-lint: source driver.rs` | `// port-lint: source driver.rs` | `driver.rs` | `port-lint provenance header matched only after fallback normalization: 'driver.rs' vs expected 'driver.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/asyncio/reactor/Windows.kt` | `// port-lint: source reactor/windows.rs` | `// port-lint: source reactor/windows.rs` | `reactor/windows.rs` | `port-lint provenance header matched only after fallback normalization: 'reactor/windows.rs' vs expected 'reactor/windows.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/asyncio/reactor/Kqueue.kt` | `// port-lint: source reactor/kqueue.rs` | `// port-lint: source reactor/kqueue.rs` | `reactor/kqueue.rs` | `port-lint provenance header matched only after fallback normalization: 'reactor/kqueue.rs' vs expected 'reactor/kqueue.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/asyncio/reactor/Unix.kt` | `// port-lint: source reactor/unix.rs` | `// port-lint: source reactor/unix.rs` | `reactor/unix.rs` | `port-lint provenance header matched only after fallback normalization: 'reactor/unix.rs' vs expected 'reactor/unix.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/asyncio/os/Unix.kt` | `// port-lint: source os/unix.rs` | `// port-lint: source os/unix.rs` | `os/unix.rs` | `port-lint provenance header matched only after fallback normalization: 'os/unix.rs' vs expected 'os/unix.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/asyncio/os/Os.kt` | `// port-lint: source os.rs` | `// port-lint: source os.rs` | `os.rs` | `port-lint provenance header matched only after fallback normalization: 'os.rs' vs expected 'os.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/asyncio/os/OsTest.kt` | `// port-lint: tests os.rs` | `// port-lint: tests os.rs` | `os.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:os.rs' vs expected 'os.rs'` |
