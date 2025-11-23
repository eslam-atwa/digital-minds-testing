import http from "k6/http";
import { Rate, Trend } from 'k6/metrics';
import { check, sleep, group } from "k6";
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

const errorRate = new Rate('errors');
const pageLoadTime = new Trend('page_load_complete');

export const options = {
  stages: [
    { target: 20, duration: "2m" },
    { target: 20, duration: "10m" },
    { target: 0, duration: "2m" },
  ],
  thresholds: {
    'http_req_duration{type:frontend}': ['p(95)<800'],
    'http_req_duration{type:api}': ['p(95)<500'],
    'errors': ['rate<0.05'],
    'checks': ['rate>0.95'],
    'page_load_complete': ['p(95)<5000']
  },
  batchPerHost: 8,
  discardResponseBodies: false
};

const BASE_URL = __ENV.BASE_URL || 'https://api.digitall-minds.com';
const MAIN_URL = 'https://digitall-minds.com/';

const httpParams = {
  timeout: '15s',
  tags: { type: 'api' },
  retries: 2
};

export default function () {
  const startTime = Date.now();
  
  group("User Journey Simulation", function () {
    // 1. Initial page visit - tagged as frontend
    const mainResp = http.get(MAIN_URL, { 
      tags: { type: 'frontend' } 
    });
    
    check(mainResp, { 
      "Main Page Loaded": (r) => r.status === 200 
    }) || errorRate.add(1);
    
    sleep(randomIntBetween(450, 650) / 1000);

    // 2. Progressive content loading
    group("Core Content Load", function() {
      const criticalAPIs = [
        ['GET', `${BASE_URL}/api/herosection/showtext/1`, null, httpParams],
        ['GET', `${BASE_URL}/api/seo/showseo/1`, null, httpParams],
        ['GET', `${BASE_URL}/api/cardcourses/show`, null, httpParams],
      ];
      
      http.batch(criticalAPIs).forEach((resp, i) => {
        check(resp, { 
          [`Core API ${i+1} OK`]: (r) => r.status === 200 
        }) || errorRate.add(1);
      });
      
      sleep(0.8);
    });
    
    // 3. Secondary content (non-blocking)
    group("Supplementary Content", function() {
      const secondaryAPIs = [
        ['GET', `${BASE_URL}/api/aboutsection/showtext/1`, null, httpParams],
        ['GET', `${BASE_URL}/api/whysection/showdata`, null, httpParams],
        ['GET', `${BASE_URL}/api/servicesection/showservice`, null, httpParams],
        ['GET', `${BASE_URL}/api/cardexams/show`, null, httpParams],
      ];
      
      http.batch(secondaryAPIs).forEach((resp, i) => {
        check(resp, { 
          [`Secondary API ${i+1} OK`]: (r) => r.status === 200 
        }) || errorRate.add(1);
      });
      
      sleep(0.4);
    });
    
    // 4. Non-essential content (simulated async)
    group("Async Content", function() {
      const asyncAPIs = [
        ['GET', `${BASE_URL}/api/commentssetion/showdatawebsite`, null, httpParams],
        ['GET', `${BASE_URL}/api/commonquestions/show`, null, httpParams],
        ['GET', `${BASE_URL}/api/socialmedia/show`, null, httpParams],
      ];
      
      // fire concurrently (http.batch already parallels calls)
      http.batch(asyncAPIs);
    });
  });
  
  const endTime = Date.now();
  pageLoadTime.add(endTime - startTime);
  
  sleep(randomIntBetween(5, 13));
}
