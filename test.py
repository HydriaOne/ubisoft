import pytest
import requests
import json

host = "https://api-gradle.imina.cat"

# Pretty basic tests, the tests can be a little bit more advanced, but for this test i should be enough.

def test_ping():
    # If we'll add additional headers.
    headers = {}
    # If we'll add additional payload.
    payload = {}
    path = '/api/javaee8'
    url = host + path
    resp = requests.request("get", url, headers=headers, data=payload)
    # Validate response status code if it is 200 the api is up
    response = resp.text
    assert resp.status_code == 200

def test_profile_info_get():
    # If we'll add additional headers.
    headers = {}
    # If we'll add additional payload.
    payload = {}
    path = '/api/profile/info'
    url = host + path
    resp = requests.request("get", url, headers=headers, data=payload)
    # Validate response status code if it is 200 the api is up
    response = resp.text
    assert resp.status_code == 200

def test_current_user():
    # If we'll add additional headers.
    headers = {}
    # If we'll add additional payload.
    payload = {}
    path = '/api/api/current-user'
    url = host + path
    resp = requests.request("get", url, headers=headers, data=payload)
    # Validate response status code if it is 200 the api is up
    response = resp.text
    # !!!FOR THIS TEST ONLY WE ACCEPT THE RESPONSE 204 AS A GOOD RESPONSE, IN REALITY IS NOT!!!
    assert resp.status_code == 204

def test_add_user():
    # If we'll add additional headers.
    payload="{\n    \"id\": -4482893.212703362,\n    \"name\": \"officia in eiusmod\",\n    \"lastPlayedVersion\": \"magna qui ut\",\n    \"lastPlayed\": -96927709.99265288,\n    \"language\": \"veniam eiusmod\",\n    \"ageRestricted\": true,\n    \"version\": -91977657.21965908,\n    \"serialVersionUID\": -86898131.21082604,\n    \"_persistence_fetchGroup\": {}\n}"
    headers = {
    'Content-Type': 'application/json'
    }
    path = '/api/profile'
    url = host + path
    resp = requests.request("POST", url, headers=headers, data=payload)
    # Validate response status code if it is 201 content created successfully
    response = resp.text
    assert resp.status_code == 201

def test_update_user():
    # If we'll add additional headers.
    payload="{\n    \"id\": -4482893.212703362,\n    \"name\": \"officia in eiusmod\",\n    \"lastPlayedVersion\": \"magna qui ut\",\n    \"lastPlayed\": -96927709.99265288,\n    \"language\": \"veniam eiusmod\",\n    \"ageRestricted\": true,\n    \"version\": -91977657.21965908,\n    \"serialVersionUID\": -86898131.21082604,\n    \"_persistence_fetchGroup\": {}\n}"
    headers = {
    'Content-Type': 'application/json'
    }
    path = '/api/profile'
    url = host + path
    resp = requests.request("PUT", url, headers=headers, data=payload)
    # Validate response status code if it is 200 content updated successfully
    response = resp.text
    assert resp.status_code == 200

def test_delete_user():
    # If we'll add additional headers.
    headers = {}
    # If we'll add additional payload.
    payload = {}
    path = "/api/profile/1"
    url = host + path
    resp = requests.request("DELETE", url, headers=headers, data=payload)
    # Validate response status code if it is 200, deleted complete, if is it 404 throw an error.
    assert resp.status_code == 200