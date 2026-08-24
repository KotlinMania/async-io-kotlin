import Testing
import AsyncIo

// Smoke test for the Kotlin → Swift Export → SPM → swift test pipeline.
@Suite
struct AsyncIoExportTests {
    @Test
    func swiftModuleLoads() {
        let timer = Timer.Companion.shared.never()
        #expect(!timer.willFire(), "Timer.never() should not fire")
    }
}

