/**
 * 格式化日期时间
 */

export function formatDatetime(date, fmt) {
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length))
    }

    let obj = {
        "M+": date.getMonth() + 1,
        "d+": date.getDay(),
        "h+": date.getHours(),
        "m+": date.getMinutes(),
        "s+": date.getSeconds()
    }

    for (let key in obj) {
        if (new RegExp(`(${key})`).test(fmt)) {
            let str = obj[key] + ''
            fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? str : padLeftZero(str))
        }
    }

    return fmt

}


function padLeftZero(str) {
    return ("00" + str).substr(str.length)
}
