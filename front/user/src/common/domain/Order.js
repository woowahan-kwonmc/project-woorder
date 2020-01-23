export class Order {
    constructor(menuNo, memberNo, quantity) {
        this._menuNo = menuNo;
        this._memberNo = memberNo;
        this._quantity = quantity;
    }

    get menuNo() {
        return this._menuNo;
    }

    get memberNo() {
        return this._memberNo;
    }

    get quantity() {
        return this._quantity;
    }
}