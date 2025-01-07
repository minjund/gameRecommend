package com.minjun.gamerecommend.application.dto;

/**
 * openid.ns: http://specs.openid.net/auth/2.0
 * openid.mode: id_res
 * openid.op_endpoint: https://steamcommunity.com/openid/login
 * openid.claimed_id: https://steamcommunity.com/openid/id/76561198002516729
 * openid.identity: https://steamcommunity.com/openid/id/76561198002516729
 * openid.return_to: The value of openid.return_to used in the original request
 * openid.response_nonce: 2020-08-27T04:44:16Zs4DPZce8qc+iPCe8JgQKB0BiIDI=
 * openid.assoc_handle: 1234567890
 * openid.signed: signed,op_endpoint,claimed_id,identity,return_to,response_nonce,assoc_handle
 * openid.sig: W0u5DRbtHE1GG0ZKXjerUZDUGmc=
 */
public record SteamCallBackInfoRequest() {
}
