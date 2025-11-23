# Performance Test Report

**Date of Test:** 23 November  
**Time:** 22:58  
**Tester:** Eslam Atwa  
**Tool Used:** k6  
**Test Location:** Columbus Load Zone  
**Duration:** 14 minutes  
**Maximum Virtual Users (VUs):** 20  
**Total Requests Made:** 14,212  

---

## 1. Test Purpose

The objective of this test was to evaluate the performance and reliability of the target system (website and APIs) under concurrent user load.  
The test measured response time, throughput, and failure rates to assess overall stability and readiness for production.

---

## 2. Key Results

| Metric | Result | Comment |
|--------|---------|----------|
| **Total Requests** | 14,212 | Total requests made during the test |
| **HTTP Failures** | 11,796 | High number of failed requests indicating instability |
| **Peak Request Rate** | 27.17 requests/second | Maximum throughput achieved during execution |
| **95th Percentile Response Time** | 145 ms | Successful requests completed within acceptable latency |
| **Thresholds** | Failed | One or more defined performance thresholds were not met |

---

## 3. Insights Summary

| Category | Score | Explanation |
|-----------|--------|-------------|
| **Best Practices** | 100 | Configuration followed k6 guidance and proper load test structure |
| **Reliability** | 23 | Large proportion of failed requests reduced overall reliability |
| **System Performance** | 100 | Successful responses were processed efficiently |

---

## 4. Observations

- The system demonstrated **good speed**, with most valid responses completing in under 150 ms.  
- However, **more than 80% of total requests failed**, suggesting significant reliability or backend issues during sustained load.  
- Failures occurred consistently throughout the test, indicating a potential **persistent system or service-level problem** rather than intermittent network issues.  
- Despite strong latency performance, the high error rate caused overall threshold failure.

---

## 5. Conclusion

The system shows **strong performance in response speed** but **poor reliability** under moderate concurrent load.  
The high failure rate indicates that the system is not yet stable enough for production conditions.  
Further review and testing will be required to validate improvements once stability issues are addressed.

---

_This report summarizes the results of the performance test conducted using k6. All metrics and insights are based on the data recorded during the testing period._
